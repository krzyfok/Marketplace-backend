package com.example.marketplace.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetUserOrdersResponseDto {
    private List<String> ordersList;

}
