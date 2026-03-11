package com.example.marketplace.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponseDto {

    private String token;
    private String username;

    public RegisterResponseDto(String token, String username) {
        this.token = token;
        this.username = username;
    }

}
