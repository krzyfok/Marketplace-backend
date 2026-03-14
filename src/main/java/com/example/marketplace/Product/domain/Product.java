package com.example.marketplace.Product.domain;

import com.example.marketplace.Product.infrastructure.ProductRepository;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = false)
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category;

    @Column(nullable = false, unique = false)
    private  Double price;

    @Column
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private int quantity;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }




}
