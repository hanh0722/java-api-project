package com.example.demo.model.CartItem;

import java.math.BigDecimal;

import com.example.demo.model.Product.Product;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class CartItem {
    private String id;
    private int quantity;
    public int getQuantity() {
        return quantity;
    }
    public String getProduct_id() {
        return id;
    }
    public void setProduct_id(String product) {
        this.id = product;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
