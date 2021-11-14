package com.example.demo.model.CartItem;

import java.math.BigDecimal;

import com.example.demo.model.Product.Product;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class CartItem {
    @DBRef
    private Product product;
    private BigDecimal quantity;

    public BigDecimal getQuantity() {
        return quantity;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
