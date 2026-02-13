package com.example.marketplace.Product.infrastructure;

import com.example.marketplace.Product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
