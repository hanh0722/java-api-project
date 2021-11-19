package com.example.demo.repository;


import java.util.Optional;

import com.example.demo.model.User.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'email': ?0}")
    Optional<User> findUserByEmail(String email);
    
    @Query("{'tokenVerify': ?0}")
    Optional<User> checkValidateUser(String token);

}
