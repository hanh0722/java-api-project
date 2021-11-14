package com.example.demo.service.User;

import java.util.List;

import com.example.demo.model.User.BasicInformation;
import com.example.demo.model.User.User;
import com.example.demo.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;

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
        return userRepository.insert(user);
    }

}
