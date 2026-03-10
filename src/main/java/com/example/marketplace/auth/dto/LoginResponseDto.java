package com.example.marketplace.auth.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String token;
    private String username;

    public LoginResponseDto(String token, String username) {
        this.token = token;
        this.username = username;
    }


}
