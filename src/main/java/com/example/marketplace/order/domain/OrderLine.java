package com.example.marketplace.order.domain;


import com.example.marketplace.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orderLines")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false
    )
    private Product product;


    @ManyToOne
    @JoinColumn(
            name = "order_id",
            nullable = false

    )
    private  Order order;

    @Column
    private BigDecimal value;

    @Column(nullable = false)
    private int quantity;

}
