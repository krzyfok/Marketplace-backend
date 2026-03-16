package com.example.marketplace.order.mapper;

import com.example.marketplace.order.dto.CreateOrderLineRequestDto;
import com.example.marketplace.order.domain.OrderLine;
import com.example.marketplace.product.infrastructure.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {

    private final ProductRepository productRepository;

    public OrderLineMapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public OrderLine mapToOrderLine(CreateOrderLineRequestDto request)
    {
            return  OrderLine.builder()
                    .quantity(request.getQuantity())
                    .product(productRepository.getReferenceById(request.getProductId()))
                    .build();

    }

}
