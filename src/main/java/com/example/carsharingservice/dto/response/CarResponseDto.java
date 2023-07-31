package com.example.carsharingservice.dto.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarResponseDto {
    private Long id;
    private String model;
    private String brand;
    private int inventory;
    private BigDecimal dailyFee;
    private String type;
}
