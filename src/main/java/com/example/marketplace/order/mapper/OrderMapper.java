package com.example.marketplace.order.mapper;

import com.example.marketplace.order.domain.Order;
import com.example.marketplace.order.dto.CreateOrderLineRequestDto;
import com.example.marketplace.order.domain.OrderLine;
import com.example.marketplace.order.dto.CreateOrderRequestDto;
import com.example.marketplace.order.dto.CreateOrderResponseDto;
import com.example.marketplace.product.infrastructure.ProductRepository;
import com.example.marketplace.user.infrastructure.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderMapper(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public OrderLine mapToOrderLine(CreateOrderLineRequestDto request, Order order)
    {
            return  OrderLine.builder()
                    .quantity(request.getQuantity())
                    .product(productRepository.getReferenceById(request.getProductId()))
                    .order(order)
                    .build();

    }

    public Order mapToOrder(CreateOrderRequestDto request){
        return Order.builder()
                .user(userRepository.getReferenceById(request.getCustomerId()))
                .build();

    }


}
