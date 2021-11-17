package com.example.demo.service.User;

import java.util.List;

import com.example.demo.model.User.BasicInformation;
import com.example.demo.model.User.User;

public interface UserService {
    User addNewUser(User user);
    User getUserByEmail(String email);
    User saveBasicInformation(String email, BasicInformation information);
    List<User> getUsers();
    User getUserById(String id);
    User checkValidateUser(String token);
    User removeOTPChecking(String id);
}
