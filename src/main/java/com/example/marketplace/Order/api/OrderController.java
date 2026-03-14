package com.example.marketplace.Order.api;


import com.example.marketplace.Order.dto.CreateOrderRequestDto;
import com.example.marketplace.Order.dto.CreateOrderResponseDto;
import com.example.marketplace.Order.service.OrderService;
import com.example.marketplace.Product.dto.NewProductRequestDto;
import com.example.marketplace.Product.dto.NewProductResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public ResponseEntity<CreateOrderResponseDto> createOrder(@RequestBody CreateOrderRequestDto request)
    {
        return  ResponseEntity.ok(new CreateOrderResponseDto());
    }



}
