package com.example.marketplace.product.mapper;

import com.example.marketplace.product.domain.Product;
import com.example.marketplace.product.dto.NewProductRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapToProduct(NewProductRequestDto request){

        return Product.builder()
                .name(request.getName())
                .model(request.getModel())
                .category(request.getCategory())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

    }
}
