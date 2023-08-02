package com.example.carsharingservice.dto.request;

import com.example.carsharingservice.model.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestInfoDto {
    private Long rentalId;
    private Payment.Type type;
}
