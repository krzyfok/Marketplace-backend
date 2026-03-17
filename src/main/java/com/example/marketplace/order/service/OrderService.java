package com.example.marketplace.order.service;

import com.example.marketplace.order.domain.Order;
import com.example.marketplace.order.dto.CreateOrderLineRequestDto;
import com.example.marketplace.order.dto.CreateOrderRequestDto;
import com.example.marketplace.order.dto.CreateOrderResponseDto;
import com.example.marketplace.order.infrastructure.OrderRepository;
import com.example.marketplace.order.domain.OrderLine;

import com.example.marketplace.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public CreateOrderResponseDto createOrder(CreateOrderRequestDto request){

        Order order = orderMapper.mapToOrder(request);
        BigDecimal totalValue= BigDecimal.ZERO;
        for(CreateOrderLineRequestDto line : request.getOrderLines())
        {
            OrderLine orderLine = orderMapper.mapToOrderLine(line,order);
            BigDecimal price = BigDecimal.valueOf(orderLine.getProduct().getPrice());
            BigDecimal value =  price.multiply(BigDecimal.valueOf(orderLine.getQuantity()));

            orderLine.setValue(value);
            totalValue = totalValue.add(value);
            order.addOrderLine(orderLine);

        }
        order.setValue(totalValue);
        orderRepository.save(order);
        return new CreateOrderResponseDto(order.getOrderNumber());
    }



}
