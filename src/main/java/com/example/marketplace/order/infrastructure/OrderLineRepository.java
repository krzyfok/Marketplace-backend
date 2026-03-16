package com.example.marketplace.order.infrastructure;

import com.example.marketplace.order.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}