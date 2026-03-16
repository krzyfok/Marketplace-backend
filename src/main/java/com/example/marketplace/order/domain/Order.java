package com.example.marketplace.order.domain;


import com.example.marketplace.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    private Long orderNumber;
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
        orderDate = LocalDateTime.now();
    }


}
