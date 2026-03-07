package com.example.marketplace.user.domain;

import com.example.marketplace.auth.domain.AuthUser;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(
            name = "auth_user_id",
            nullable = false,
            unique = true
    )
    private AuthUser authUser;



    @Column(nullable = false, unique = false)
    private String firstName;

    @Column(nullable = false, unique = false)
    private String lastName;

    @Column(nullable = false, unique = false)
    private LocalDateTime createdAt;

    

}
