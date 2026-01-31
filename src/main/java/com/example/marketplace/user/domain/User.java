package com.example.marketplace.user.domain;

import com.example.marketplace.auth.domain.AuthUser;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    public User() {
    }

    public User(AuthUser authUser, String firstName, String lastName) {
        this.authUser = authUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt  = LocalDateTime.now();
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
