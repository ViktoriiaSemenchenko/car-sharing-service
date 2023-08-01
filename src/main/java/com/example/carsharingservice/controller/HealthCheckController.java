package com.example.carsharingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    @Operation(summary = "Health check")
    public String healthCheck() {
        return "Ok";
    }
}
