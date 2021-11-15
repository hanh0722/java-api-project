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
    public User saveBasicInformation(String email, BasicInformation information) {
        User user = getUserByEmail(email);
        user.setBasic_information(information);
        User userAfterUpdate = userRepository.save(user);
        return userAfterUpdate;
    }

    @Override
    public User addNewUser(User user) {
        // TODO Auto-generated method stub 
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.insert(user);
    }
}
