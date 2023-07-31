package com.example.carsharingservice.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDto {
    private String model;
    private String brand;
    private int inventory;
    private BigDecimal dailyFee;
    private String type;
}
