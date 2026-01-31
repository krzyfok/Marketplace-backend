package com.example.marketplace.auth.service;

import com.example.marketplace.auth.domain.AuthUser;
import com.example.marketplace.auth.dto.LoginRequest;
import com.example.marketplace.auth.dto.LoginResponse;
import com.example.marketplace.auth.dto.RegisterRequest;
import com.example.marketplace.auth.dto.RegisterResponse;
import com.example.marketplace.auth.infrastructure.AuthUserRepository;
import com.example.marketplace.auth.infrastructure.JwtTokenProvider;
import com.example.marketplace.user.domain.User;
import com.example.marketplace.user.infrastructure.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final AuthUserRepository authUserRepo;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtProvider;

    public AuthService(AuthUserRepository authUserRepo,UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtProvider) {
        this.authUserRepo = authUserRepo;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        AuthUser authUser = new AuthUser(request.getUsername(),request.getEmail(),passwordEncoder.encode(request.getPassword()));
        authUserRepo.save(authUser);

        User user = new User(authUser,request.getName(),request.getSurname());
        userRepository.save(user);

        return new RegisterResponse(jwtProvider.generateToken(authUser));

    }
    @Transactional
    public LoginResponse login(LoginRequest request) {
        AuthUser user = authUserRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        user.setLastloginAt(LocalDateTime.now());
        return  new LoginResponse(jwtProvider.generateToken(user));
    }

}
