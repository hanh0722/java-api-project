package com.example.demo.repository;


import java.util.Optional;

import com.example.demo.model.User.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'email'}: ?0")
    Optional<User> findUserByEmail(String email);
}
