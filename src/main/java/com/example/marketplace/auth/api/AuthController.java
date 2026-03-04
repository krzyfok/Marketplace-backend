package com.example.marketplace.auth.api;


import com.example.marketplace.auth.dto.LoginRequestDto;
import com.example.marketplace.auth.dto.LoginResponseDto;
import com.example.marketplace.auth.dto.RegisterRequestDto;
import com.example.marketplace.auth.dto.RegisterResponseDto;
import com.example.marketplace.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponseDto register(@RequestBody RegisterRequestDto request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        return authService.login(request);

    }
}