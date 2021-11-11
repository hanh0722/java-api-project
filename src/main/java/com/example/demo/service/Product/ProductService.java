package com.example.demo.service.Product;

import com.example.demo.model.Product.Product;
import com.example.demo.repository.ProductRepository.ProductRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.insert(product);
    }
    
}
