package com.example.marketplace.order.dto;

import lombok.*;

@Getter
@Setter
public class CreateOrderLineRequestDto {

    private Long productId;
    private int quantity;
}
