package com.example.carsharingservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDto {
    @NotNull
    private String model;
    @NotNull
    private String brand;
    @NotNull
    @Positive
    private int inventory;
    @NotNull
    @Positive
    private BigDecimal dailyFee;
    @NotNull
    private String type;
}
