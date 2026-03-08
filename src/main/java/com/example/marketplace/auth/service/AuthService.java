package com.example.marketplace.auth.service;

import com.example.marketplace.auth.domain.AuthUser;
import com.example.marketplace.auth.dto.LoginRequestDto;
import com.example.marketplace.auth.dto.LoginResponseDto;
import com.example.marketplace.auth.dto.RegisterRequestDto;
import com.example.marketplace.auth.dto.RegisterResponseDto;
import com.example.marketplace.auth.infrastructure.AuthUserRepository;
import com.example.marketplace.auth.mapper.AuthUserMapper;
import com.example.marketplace.security.jwt.JwtTokenProvider;
import com.example.marketplace.user.domain.User;
import com.example.marketplace.user.infrastructure.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final AuthUserRepository authUserRepo;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtProvider;
    private final AuthUserMapper authUserMapper;

    public AuthService(AuthUserRepository authUserRepo,UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtProvider, AuthUserMapper authUserMapper) {
        this.authUserRepo = authUserRepo;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.authUserMapper =authUserMapper;
    }

    @Transactional
    public RegisterResponseDto register(RegisterRequestDto request) {
        AuthUser authUser = authUserMapper.mapFromRegisterRequestDtoToAuthUser(request);
        authUserRepo.save(authUser);

        User user = User.builder()
                    .authUser(authUser)
                    .firstName(request.getName())
                    .lastName(request.getSurname())
                    .build();

        userRepository.save(user);

        return new RegisterResponseDto(jwtProvider.generateToken(authUser));

    }
    @Transactional
    public LoginResponseDto login(LoginRequestDto request) {
        AuthUser user = authUserRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }
        user.setLastloginAt(LocalDateTime.now());
        return  new LoginResponseDto(jwtProvider.generateToken(user));
    }

}
