package com.example.demo.repository.ProductRepository;

import com.example.demo.model.Product.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    
}
