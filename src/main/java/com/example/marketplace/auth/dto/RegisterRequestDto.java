package com.example.marketplace.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;


}
