package com.example.demo.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.demo.model.CartItem.CartItem;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;


    @Indexed(name = "email", unique = true)
    @Field(name = "email")
    private String email;
    private String password;
    private List<CartItem> cart;
    private String phone;
    private Collection<String> Role = new ArrayList<>();
    private String tokenVefiry;
    private String OTP;
    private BasicInformation basic_information;
    private String avatar;
    private Boolean verified;
    public void setUser( String name, String email, String password, List<CartItem> cart, String phone, Collection<String> role, String tokenVefiry, String OTP, BasicInformation basic_information, String avatar, Boolean verified) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cart = cart;
        this.phone = phone;
        Role = role;
        this.tokenVefiry = tokenVefiry;
        this.OTP = OTP;
        this.basic_information = basic_information;
        this.avatar = avatar;
        this.verified = verified;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String oTP) {
        OTP = oTP;
    }

    public String getTokenVefiry() {
        return this.tokenVefiry;
    }

    public void setTokenVefiry(String tokenVefiry) {
        this.tokenVefiry = tokenVefiry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Collection<String> getRole() {
        return Role;
    }

    public void setRole(Collection<String> role) {
        Role = role;
    }

    public String get_id() {
        return id;
    }

    public void set_id(String id) {
        this.id = id;
    }

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
