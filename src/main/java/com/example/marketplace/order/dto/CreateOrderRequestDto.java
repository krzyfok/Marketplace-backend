package com.example.marketplace.order.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
public class CreateOrderRequestDto {

    private Long customerId;
    private List<CreateOrderLineRequestDto> orderLines;
}
