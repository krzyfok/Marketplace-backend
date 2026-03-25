package com.example.marketplace.product.dto;

import com.example.marketplace.product.domain.ProductCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String model;
    private ProductCategory category;
    private  Double price;
}
