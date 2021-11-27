package com.example.demo.service.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.demo.model.User.BasicInformation;
import com.example.demo.model.User.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImplement implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user is not existed in db"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public User getUserById(String id) {
        // TODO Auto-generated method stub
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User is not existed"));
    }

    @Override
    public User getUserByEmail(String email) {
        // TODO Auto-generated method stub
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cannot find user with the email"));
    }

    @Override
    public List<User> getUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    @Override
    public User checkValidateUser(String token) {
        // TODO Auto-generated method stub
        User user = userRepository.checkValidateUser(token).orElseThrow(() -> new RuntimeException("user is not validation with token"));
        return user;
    }

    @Override
    public User removeOTPChecking(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user is not existed"));
        user.setOTP(null);
        user.setVerified(true);
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public User updatePasswordUser(String password, User user) {
        user.setPassword(passwordEncoder.encode(password));
        user.setTokenChangePassword(null);
        User newUserAfterUpdate = userRepository.save(user);
        return newUserAfterUpdate;
    }

    @Override
    public User saveChangeUser(String email, String name, String avatar, String phone ,BasicInformation information) {
        User findUserByEmail = getUserByEmail(email);
        findUserByEmail.setAvatar(avatar);
        findUserByEmail.setName(name);
        findUserByEmail.setBasic_information(information);
        findUserByEmail.setPhone(phone);
        User userAfterUpdate = userRepository.save(findUserByEmail);
        return userAfterUpdate;
    }

    @Override
    public User addNewUser(User user) {
        // TODO Auto-generated method stub 
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        ArrayList<String> role = new ArrayList<>();
        user.setVerified(false);
        role.add("ROLE_USER");
        user.setRole(role);
        return userRepository.insert(user);
    }
    @Override
    public void saveUserInfo(User user){
        userRepository.save(user);
    }
}
