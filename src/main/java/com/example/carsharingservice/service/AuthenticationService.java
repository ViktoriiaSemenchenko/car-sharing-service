package com.example.carsharingservice.service;

import com.example.carsharingservice.dto.request.UserLoginRequestDto;
import com.example.carsharingservice.dto.request.UserRegisterRequestDto;
import com.example.carsharingservice.dto.response.JwtAuthenticationResponseDto;

public interface AuthenticationService {
    JwtAuthenticationResponseDto register(UserRegisterRequestDto request);

    JwtAuthenticationResponseDto login(UserLoginRequestDto request);
}
