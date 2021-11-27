package com.example.demo.service.Product;

import java.util.Arrays;
import java.util.List;

import com.example.demo.model.Product.Product;
import com.example.demo.model.Product.ProductCategory;
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

    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public List<ProductCategory> getAllCategories () {
        return Arrays.asList(ProductCategory.values());
    }
}
