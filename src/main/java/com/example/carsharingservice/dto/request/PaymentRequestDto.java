package com.example.carsharingservice.dto.request;

import com.example.carsharingservice.model.Rental;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.net.URL;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {
    @NotNull
    private String status;
    @NotNull
    private String type;
    @Positive
    private Rental rental;
    @NotNull
    private URL url;
    @NotNull
    private String sessionId;
    @NotNull
    @Positive
    private BigDecimal paymentAmount;
}
