package com.example.marketplace.product.dto;


import com.example.marketplace.product.domain.ProductCategory;
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
