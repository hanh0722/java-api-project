package com.example.demo.model.User;

import java.util.List;

import com.example.demo.model.CartItem.CartItem;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String _id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String password;
    private List<CartItem> cart;
    private String phone;
    private BasicInformation basic_information;
    private String avatar;

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BasicInformation getBasic_information() {
        return basic_information;
    }

    public void setBasic_information(BasicInformation basic_information) {
        this.basic_information = basic_information;
    }
}
