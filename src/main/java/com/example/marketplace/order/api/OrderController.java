package com.example.marketplace.order.api;


import com.example.marketplace.order.dto.CreateOrderRequestDto;
import com.example.marketplace.order.dto.CreateOrderResponseDto;
import com.example.marketplace.order.dto.GetUserOrdersResponseDto;
import com.example.marketplace.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponseDto> createOrder(@RequestBody CreateOrderRequestDto request)
    {
        return  ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<GetUserOrdersResponseDto> getUserOrders(@RequestParam Long userId, @RequestParam(required = false) String status)
    {
        return ResponseEntity.ok(orderService.getUserOrder(userId));
    }



}
