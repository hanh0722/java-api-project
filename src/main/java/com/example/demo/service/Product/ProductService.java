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
    public Product getProductById(String id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product is not existed"));
    }

    public Product updateProductById(String id, Product product){
        Product oldProduct = getProductById(id);
        oldProduct.setTitle(product.getTitle());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setImageUrls(product.getImageUrls());
        oldProduct.setInStock(product.getInStock());
        oldProduct.setLastPrice(product.getLastPrice());
        oldProduct.setRegularPrice(product.getRegularPrice());
        oldProduct.setSalePercent(product.getSalePercent());
        oldProduct.setProductCategory(product.getCategory());

        Product newProduct = productRepository.save(oldProduct);
        return newProduct;
    }
}
