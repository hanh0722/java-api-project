package com.example.demo.service.Discount;

import com.example.demo.model.Discount.Discount;
import com.example.demo.repository.DiscountRepository.DiscountRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;

    public Discount getDiscountByName(String name){
        return discountRepository.getDiscountByName(name).orElseThrow(() -> new RuntimeException("discount is not existed"));
    }

    public Discount addDiscountToDB(Discount discount){
        Discount dc = discountRepository.save(discount);
        return dc;
    }

}
