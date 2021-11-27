package com.example.demo.controller.Discount;

import com.example.demo.model.Discount.Discount;
import com.example.demo.repository.DiscountRepository.DiscountRepository;
import com.example.demo.service.Discount.DiscountService;
import com.example.demo.util.ErrrorException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/discount")
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping("/post")
    public ResponseEntity<?> getDiscountByName(@RequestBody String name) {
        try {

            Discount discount = discountService.getDiscountByName(name);
            return ResponseEntity.ok().body(discount);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrrorException(404, "discount is not existed"));
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addDiscountToDB(@RequestBody Discount discount){
        try{
            Discount newDiscount = discountService.addDiscountToDB(discount);
            return ResponseEntity.ok().body(newDiscount);
        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrrorException(500, "cannot add discount"));
        }
    }
}
