package com.example.demo.model.Discount;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "discount")
@AllArgsConstructor
@Getter
@Setter
public class Discount {
    @Id
    private String id;
    private String name;
    private Float percent;
}
