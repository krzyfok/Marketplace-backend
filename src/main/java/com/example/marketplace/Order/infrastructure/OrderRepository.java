package com.example.marketplace.Order.infrastructure;

import com.example.marketplace.Order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}