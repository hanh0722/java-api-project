package com.example.demo.repository.DiscountRepository;

import java.util.Optional;

import com.example.demo.model.Discount.Discount;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface DiscountRepository extends MongoRepository<Discount, String> {
    @Query("{'name': ?0}")
    Optional<Discount> getDiscountByName(String name);
}
