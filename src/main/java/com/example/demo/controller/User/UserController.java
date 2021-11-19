package com.example.demo.controller.User;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.model.CartItem.CartItem;
import com.example.demo.model.User.User;
import com.example.demo.service.User.UserService;
import com.example.demo.util.ErrrorException;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }    
    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody User user){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user").toUriString());
            ArrayList<CartItem> cart = new ArrayList<>();
            user.setCart(cart);
            
            // register always have no cart
            return ResponseEntity.created(uri).body(userService.addNewUser(user));
        }catch(Exception error){
            return ResponseEntity.badRequest().body(new ErrrorException(400, "User existed with that email"));
        }
    }
    @GetMapping("id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        try{
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrrorException(404, "User is not existed"));
        }
    }
    @GetMapping("get/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        try{
            User user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrrorException(404, "User is not existed"));
        }
    }
    @GetMapping("/verify")
    public ResponseEntity<?> checkUserIsValid(@RequestParam String token){
        try{
            User user = userService.checkValidateUser(token);
            if(user.getVerified()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrrorException(409, "User verified"));
            }
            return ResponseEntity.ok().body(user);

        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrrorException(422, "user is not validation"));
        }
    }
    @PostMapping("/validate")
    public ResponseEntity<?> checkOTPOfUser(@RequestBody Map<String, String> context ){
        try{
            String id = context.get("id");
            String token = context.get("OTP");
            User user = userService.getUserById(id);
            Boolean matchOTP = user.getOTP().equals(token);
            if(!matchOTP){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrrorException(422, "OTP is not correct"));
            }
            User userAfterUpdate = userService.removeOTPChecking(id);
            return ResponseEntity.ok().body(userAfterUpdate);
        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrrorException(422, "user is not existed"));
        }
    }
    @GetMapping("/reset")
    public ResponseEntity<?> getResetOfUser(@RequestParam Map<String, String> context){
        try{
            String id = context.get("id");
            String token = context.get("token");
            if(id.isEmpty() || token.isEmpty()){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrrorException(403, "user is not authorization"));
            }
            User user = userService.getUserById(id);
            Boolean matchToken = user.getTokenChangePassword().equals(token);
            if(!matchToken){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrrorException(422, "token is not valid"));
            }
            return ResponseEntity.ok().body(user);
        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrrorException(404, "user is not existed"));
        }
    }
    @PutMapping("/reset-password")
    public ResponseEntity<?> resetUserPassword(@RequestBody Map<String, String> context){
        try{
            String id = context.get("id");
            String password = context.get("password");
            String token = context.get("token");
            User user = userService.getUserById(id);
            if(!user.getTokenChangePassword().equals(token)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrrorException(400, "token is not matched"));
            }
            userService.updatePasswordUser(password, user);
            return ResponseEntity.ok().body(new ErrrorException(200, "Successfully"));
        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrrorException(404, "user is not existed"));
        }
    }
    @PostMapping("/addtocart/{user_id}")
    public ResponseEntity<?> addToCart(@PathVariable String user_id,@RequestBody CartItem cartItem){
        try {

            User user = userService.getUserById(user_id);
            ArrayList<CartItem> cartList = (ArrayList<CartItem>) user.getCart();
            int d = 0;
            for (CartItem cart : cartList) {
                if (cart.getProduct_id().equals(cartItem.getProduct_id())) {
                    d = 1;
                    cart.setQuantity(cart.getQuantity() + cartItem.getQuantity());
                }
            }
            if (d==0){
                cartList.add(cartItem);
            }
            user.setCart(cartList);
            userService.saveUserInfo(user);

            return ResponseEntity.ok().body(user);
        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrrorException(404, "add to cart fail"));
        }
    }
    @PostMapping("/removecart/{user_id}")
    public ResponseEntity<?> removeCart(@PathVariable String user_id,@RequestBody CartItem cartItem){
        try {

            User user = userService.getUserById(user_id);
            ArrayList<CartItem> cartList = (ArrayList<CartItem>) user.getCart();
            ArrayList<CartItem> cartListt = new ArrayList<>();
            for (CartItem cart : cartList) {
                if (cart.getProduct_id().equals(cartItem.getProduct_id())) {
                    cart.setQuantity(cart.getQuantity() - cartItem.getQuantity());
                }
                if (cart.getQuantity()>0){
                    cartListt.add(cart);
                }
            }
            user.setCart(cartListt);
            userService.saveUserInfo(user);
            return ResponseEntity.ok().body(user);
        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrrorException(404, "remove fail"));
        }
    }
}
