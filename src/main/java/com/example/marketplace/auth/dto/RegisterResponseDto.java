package com.example.marketplace.auth.dto;

public class RegisterResponseDto {
    private String token;

    public RegisterResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
