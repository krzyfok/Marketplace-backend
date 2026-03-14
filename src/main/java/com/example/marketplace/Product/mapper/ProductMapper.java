package com.example.marketplace.Product.mapper;

import com.example.marketplace.Product.domain.Product;
import com.example.marketplace.Product.dto.NewProductRequestDto;
import com.example.marketplace.Product.dto.NewProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapFromNewProductRequestDtoToProduct(NewProductRequestDto request){

        return Product.builder()
                .name(request.getName())
                .model(request.getModel())
                .category(request.getCategory())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

    }
}
