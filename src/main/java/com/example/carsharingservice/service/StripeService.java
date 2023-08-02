package com.example.carsharingservice.service;

import com.example.carsharingservice.dto.request.PaymentRequestInfoDto;
import com.example.carsharingservice.dto.response.PaymentResponseDto;
import com.example.carsharingservice.model.Payment;
import com.stripe.param.checkout.SessionCreateParams;

public interface StripeService {
    SessionCreateParams createPaymentSession(Long rentalId, Payment.Type type);

    PaymentResponseDto getPaymentFromSession(SessionCreateParams params,
                                             PaymentRequestInfoDto paymentRequestInfoDto);
}
