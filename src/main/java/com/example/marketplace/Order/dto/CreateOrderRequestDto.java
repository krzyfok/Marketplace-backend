package com.example.marketplace.Order.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
public class CreateOrderRequestDto {

    private Long customerId;
    private List<CreateOrderLineRequestDto> orderLines;
}
