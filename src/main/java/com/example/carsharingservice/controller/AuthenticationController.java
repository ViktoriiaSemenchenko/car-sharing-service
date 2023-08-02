package com.example.carsharingservice.controller;

import com.example.carsharingservice.dto.request.SignUpRequestDto;
import com.example.carsharingservice.dto.request.SigninRequestDto;
import com.example.carsharingservice.dto.response.JwtAuthenticationResponseDto;
import com.example.carsharingservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponseDto> signup(@RequestBody
                                                                   SignUpRequestDto request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponseDto> signin(@RequestBody
                                                                   SigninRequestDto request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
