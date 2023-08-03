package com.example.carsharingservice.service.impl;

import com.example.carsharingservice.dto.request.UserLoginRequestDto;
import com.example.carsharingservice.dto.request.UserRegisterRequestDto;
import com.example.carsharingservice.dto.response.JwtAuthenticationResponseDto;
import com.example.carsharingservice.model.User;
import com.example.carsharingservice.repository.UserRepository;
import com.example.carsharingservice.security.JwtService;
import com.example.carsharingservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public JwtAuthenticationResponseDto register(UserRegisterRequestDto request) {
        return getJwtAuthenticationResponseDto(request, User.Role.CUSTOMER);
    }

    @Override
    public JwtAuthenticationResponseDto registerManager(UserRegisterRequestDto request) {
        return getJwtAuthenticationResponseDto(request, User.Role.MANAGER);
    }

    private JwtAuthenticationResponseDto getJwtAuthenticationResponseDto(
            UserRegisterRequestDto request, User.Role role) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponseDto.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponseDto login(UserLoginRequestDto request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponseDto.builder().token(jwt).build();
    }
}
