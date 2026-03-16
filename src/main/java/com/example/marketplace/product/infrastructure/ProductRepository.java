package com.example.marketplace.product.infrastructure;

import com.example.marketplace.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
