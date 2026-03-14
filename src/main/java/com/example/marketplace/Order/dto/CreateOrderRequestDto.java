package com.example.marketplace.Order.dto;

import java.util.List;

public class CreateOrderRequestDto {

    private Long customerId;
    private List<CreateOrderLineRequestDto> orderLines;
}
