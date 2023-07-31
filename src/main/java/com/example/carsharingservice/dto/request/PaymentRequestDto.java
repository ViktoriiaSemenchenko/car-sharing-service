package com.example.carsharingservice.dto.request;

import java.math.BigDecimal;
import java.net.URL;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {
    private String status;
    private String type;
    private Long rentalId;
    private URL sessionUrl;
    private String sessionId;
    private BigDecimal amountToPay;
}
