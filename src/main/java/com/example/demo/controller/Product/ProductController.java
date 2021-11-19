package com.example.demo.controller.Product;

import java.util.List;

import com.example.demo.model.Product.Product;
import com.example.demo.service.Product.ProductService;
import com.example.demo.util.ErrrorException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        try{
            Product newProduct = productService.addProduct(product);
            return ResponseEntity.ok(newProduct);
        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrrorException(500, "Cannot add product"));
        }
    }
    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }
}
