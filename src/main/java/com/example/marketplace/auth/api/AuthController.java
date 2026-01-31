package com.example.marketplace.auth.api;


import com.example.marketplace.auth.dto.LoginRequest;
import com.example.marketplace.auth.dto.LoginResponse;
import com.example.marketplace.auth.dto.RegisterRequest;
import com.example.marketplace.auth.dto.RegisterResponse;
import com.example.marketplace.auth.service.AuthService;
import org.apache.juli.logging.Log;
import org.springframework.http.ResponseEntity;
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
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);

    }
}