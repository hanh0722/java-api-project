package com.example.demo.model.Product;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

@Document(collection = "products")
@AllArgsConstructor
public class Product {
    // add constructor and define field, ID, document
    @Id
    private String id;
    private String title;
    private String description;
    private List<String> image_urls;
    private Boolean in_stock;
    private BigDecimal regular_price;
    private ProductCategory category;
    private BigDecimal sale_percent;
    private BigDecimal last_price;
    

    public String getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getDescription(){
        return this.description;
    }
    public List<String> getImageUrls(){
        return this.image_urls;
    }
    public Boolean getInStock(){
        return this.in_stock;
    }
    public BigDecimal getRegularPrice(){
        return this.regular_price;
    }
    public ProductCategory getCategory(){
        return this.category;
    }
    public BigDecimal getSalePercent(){
        return this.sale_percent;
    }
    public BigDecimal getLastPrice(){
        return this.last_price;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setImageUrls(List<String> image_urls){
        this.image_urls = image_urls;
    }
    public void setInStock(Boolean in_stock){
        this.in_stock = in_stock;
    }
    public void setRegularPrice(BigDecimal regular_price){
        this.regular_price = regular_price;
    }
    public void setProductCategory(ProductCategory category){
        this.category = category;
    }
    public void setSalePercent(BigDecimal percent){
        this.sale_percent = percent;
    }
    public void setLastPrice(BigDecimal last_price){
        this.last_price = last_price;
    }
}
