package com.example.carsharingservice.dto.response;

import com.example.carsharingservice.model.Rental;
import java.math.BigDecimal;
import java.net.URL;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {
    private Long id;
    private String status;
    private String type;
    private Rental rental;
    private URL url;
    private String sessionId;
    private BigDecimal paymentAmount;
}
