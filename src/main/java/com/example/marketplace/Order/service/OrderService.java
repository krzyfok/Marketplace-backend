package com.example.marketplace.Order.service;

import com.example.marketplace.Order.infrastructure.OrderRepository;

public class OrderService {

    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }
}
