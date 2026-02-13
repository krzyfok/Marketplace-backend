package com.example.marketplace.auth.domain;


import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name="auth_users")
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = false)
    private String email;

    @Column(nullable = false, unique = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthUserRole role;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime lastloginAt;

    public AuthUser() {}

    public AuthUser(String username, String email, String password, AuthUserRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getLastloginAt() {
        return lastloginAt;
    }

    public void setLastloginAt(LocalDateTime lastloginAt) {
        this.lastloginAt = lastloginAt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
