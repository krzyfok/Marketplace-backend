package com.example.marketplace.auth.service;

import com.example.marketplace.auth.domain.AuthUser;
import com.example.marketplace.auth.dto.LoginRequest;
import com.example.marketplace.auth.dto.RegisterRequest;
import com.example.marketplace.auth.infrastructure.AuthUserRepository;
import com.example.marketplace.auth.infrastructure.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthUserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtProvider;

    public AuthService(AuthUserRepository userRepo, PasswordEncoder passwordEncoder, JwtTokenProvider jwtProvider) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }


    public AuthUser register(RegisterRequest request) {
        AuthUser user = new AuthUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        return userRepo.save(user);
    }

    public String login(LoginRequest request) {
        AuthUser user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return jwtProvider.generateToken(user);
    }

}
