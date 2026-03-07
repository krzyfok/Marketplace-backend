package com.example.marketplace.auth;



import com.example.marketplace.auth.domain.AuthUser;
import com.example.marketplace.auth.domain.AuthUserRole;
import com.example.marketplace.auth.dto.LoginRequestDto;
import com.example.marketplace.auth.dto.LoginResponseDto;
import com.example.marketplace.auth.dto.RegisterRequestDto;
import com.example.marketplace.auth.dto.RegisterResponseDto;
import com.example.marketplace.auth.infrastructure.AuthUserRepository;
import com.example.marketplace.auth.infrastructure.JwtTokenProvider;
import com.example.marketplace.auth.mapper.AuthUserMapper;
import com.example.marketplace.auth.service.AuthService;
import com.example.marketplace.user.domain.User;
import com.example.marketplace.user.infrastructure.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    private AuthUserRepository authUserRepo;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtProvider;
    private AuthService authService;
    private AuthUserMapper authUserMapper;

    @BeforeEach
    void setUp() {
        authUserRepo = mock(AuthUserRepository.class);
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtProvider = mock(JwtTokenProvider.class);
        authUserMapper = mock(AuthUserMapper.class);
        authService = new AuthService(authUserRepo, userRepository, passwordEncoder, jwtProvider, authUserMapper);
    }

    @Test
    void register_shouldSaveAuthUserAndUserAndReturnToken() {
        RegisterRequestDto request = new RegisterRequestDto();
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("secret");
        request.setName("John");
        request.setSurname("Doe");

        when(authUserMapper.mapFromRegisterRequestDtoToAuthUser(any(RegisterRequestDto.class)))
                .thenAnswer(invocation -> {
                    RegisterRequestDto req = invocation.getArgument(0);
                    return AuthUser.builder()
                            .username(req.getUsername())
                            .email(req.getEmail())
                            .password("hashedSecret")
                            .role(AuthUserRole.USER)
                            .build();
                });
        when(jwtProvider.generateToken(any(AuthUser.class))).thenReturn("fake-jwt");

        RegisterResponseDto response = authService.register(request);


        ArgumentCaptor<AuthUser> authUserCaptor = ArgumentCaptor.forClass(AuthUser.class);
        verify(authUserRepo).save(authUserCaptor.capture());
        AuthUser savedAuthUser = authUserCaptor.getValue();
        assertEquals("john", savedAuthUser.getUsername());
        assertEquals("john@example.com", savedAuthUser.getEmail());
        assertEquals("hashedSecret", savedAuthUser.getPassword());


        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());
        assertEquals(savedAuthUser, savedUser.getAuthUser());
        assertEquals("fake-jwt", response.getToken());
    }

    @Test
    void login_shouldReturnTokenIfPasswordMatches() {
        AuthUser authUser = AuthUser.builder()
                .username("john")
                .email("john@example.com")
                .password("hashed")
                .role(AuthUserRole.USER)
                .build();
        when(authUserRepo.findByUsername("john")).thenReturn(Optional.of(authUser));
        when(passwordEncoder.matches("secret", "hashed")).thenReturn(true);
        when(jwtProvider.generateToken(authUser)).thenReturn("fake-jwt");

        LoginRequestDto request = new LoginRequestDto();
        request.setUsername("john");
        request.setPassword("secret");

        LoginResponseDto response = authService.login(request);

        assertEquals("fake-jwt", response.getToken());
        assertNotNull(authUser.getLastloginAt());
    }

    @Test
    void login_shouldThrowExceptionIfUserNotFound() {
        when(authUserRepo.findByUsername("unknown")).thenReturn(Optional.empty());

        LoginRequestDto request = new LoginRequestDto();
        request.setUsername("unknown");
        request.setPassword("secret");

        RuntimeException ex = assertThrows(RuntimeException.class, () -> authService.login(request));
        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void login_shouldThrowExceptionIfPasswordInvalid() {
        AuthUser authUser = AuthUser.builder()
                .username("john")
                .email("john@example.com")
                .password("hashed")
                .role(AuthUserRole.USER)
                .build();
        when(authUserRepo.findByUsername("john")).thenReturn(Optional.of(authUser));
        when(passwordEncoder.matches("wrong", "hashed")).thenReturn(false);

        LoginRequestDto request = new LoginRequestDto();
        request.setUsername("john");
        request.setPassword("wrong");

        RuntimeException ex = assertThrows(RuntimeException.class, () -> authService.login(request));
        assertEquals("Invalid password", ex.getMessage());
    }
}
