package com.example.marketplace.order.service;

import com.example.marketplace.order.dto.CreateOrderLineRequestDto;
import com.example.marketplace.order.dto.CreateOrderRequestDto;
import com.example.marketplace.order.dto.CreateOrderResponseDto;
import com.example.marketplace.order.infrastructure.OrderRepository;
import com.example.marketplace.order.domain.OrderLine;
import com.example.marketplace.order.infrastructure.OrderLineRepository;
import com.example.marketplace.order.mapper.OrderLineMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineMapper orderLineMapper;
    private final OrderLineRepository orderLineRepository;

    public OrderService(OrderRepository orderRepository, OrderLineMapper orderLineMapper, OrderLineRepository orderLineRepository) {
        this.orderRepository = orderRepository;
        this.orderLineMapper = orderLineMapper;
        this.orderLineRepository = orderLineRepository;
    }

    public CreateOrderResponseDto createOrder(CreateOrderRequestDto request){

        for(CreateOrderLineRequestDto line : request.getOrderLines())
        {
            OrderLine orderLine = orderLineMapper.mapToOrderLine(line);
            orderLineRepository.save(orderLine);

        }
        return new CreateOrderResponseDto();
    }



}
