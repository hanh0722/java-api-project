package com.example.demo.service.User;

import java.util.List;

import com.example.demo.model.User.BasicInformation;
import com.example.demo.model.User.User;

public interface UserService {
    User addNewUser(User user);
    User getUserByEmail(String email);
    User saveChangeUser(String email, String name, String avatar, String phone ,BasicInformation information);
    List<User> getUsers();
    User getUserById(String id);
    User checkValidateUser(String token);
    User removeOTPChecking(String id);
    User updatePasswordUser(String password, User user);
    void saveUserInfo(User user);
}
