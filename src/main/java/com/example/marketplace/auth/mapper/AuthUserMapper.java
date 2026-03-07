package com.example.marketplace.auth.mapper;

import com.example.marketplace.auth.domain.AuthUser;
import com.example.marketplace.auth.domain.AuthUserRole;
import com.example.marketplace.auth.dto.RegisterRequestDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthUserMapper {
    private final PasswordEncoder passwordEncoder;

    public AuthUserMapper(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder =passwordEncoder;
    }

    public AuthUser mapFromRegisterRequestDtoToAuthUser(RegisterRequestDto request){

        return AuthUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .role(AuthUserRole.USER)
                .build();}

}
