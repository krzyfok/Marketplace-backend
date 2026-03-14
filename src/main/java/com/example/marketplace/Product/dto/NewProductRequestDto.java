package com.example.marketplace.Product.dto;


import com.example.marketplace.Product.domain.ProductCategory;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NewProductRequestDto {


    private String name;
    private String model;
    private ProductCategory category;
    private  Double price;
    private int quantity;

}
