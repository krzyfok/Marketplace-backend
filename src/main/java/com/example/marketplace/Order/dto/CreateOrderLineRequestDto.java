package com.example.marketplace.Order.dto;

import lombok.*;

@Getter
@Setter
public class CreateOrderLineRequestDto {

    private Long productId;
    private int quantity;
}
