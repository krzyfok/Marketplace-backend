package com.example.marketplace.order.domain;


import com.example.marketplace.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

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


    @OneToOne
    @JoinColumn(
            name = "product_id",
            nullable = false
    )
    private Product product;


    @Column(nullable = false)
    private int quantity;

}
