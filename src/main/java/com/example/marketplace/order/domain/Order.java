package com.example.marketplace.order.domain;


import com.example.marketplace.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String orderNumber;
    @Column(nullable = false)
    private LocalDateTime orderDate;

    @OneToOne(optional = false)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;

    @Column(nullable = false)
    private double value;
    @Column(nullable = false)
    private String status;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        orderDate = now;
        orderNumber = generateOrderNumber(now);
    }

    private String generateOrderNumber(LocalDateTime now) {
        String date = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String random = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "ORD-" + date + "-" + random;
    }


}
