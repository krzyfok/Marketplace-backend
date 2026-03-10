package com.example.marketplace.auth.dto;

public class RegisterResponseDto {
    private String token;
    private String username;

    public RegisterResponseDto(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }
}
