package com.example.carsharingservice.service;

import com.example.carsharingservice.dto.request.SignUpRequestDto;
import com.example.carsharingservice.dto.request.SigninRequestDto;
import com.example.carsharingservice.dto.response.JwtAuthenticationResponseDto;

public interface AuthenticationService {
    JwtAuthenticationResponseDto signup(SignUpRequestDto request);

    JwtAuthenticationResponseDto signin(SigninRequestDto request);
}
