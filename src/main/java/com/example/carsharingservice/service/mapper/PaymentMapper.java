package com.example.carsharingservice.service.mapper;

import com.example.carsharingservice.dto.request.PaymentRequestDto;
import com.example.carsharingservice.dto.response.PaymentResponseDto;
import com.example.carsharingservice.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper implements DtoMapper<PaymentRequestDto, PaymentResponseDto, Payment> {
    @Override
    public PaymentResponseDto toDto(Payment payment) {
        PaymentResponseDto responseDto = new PaymentResponseDto();
        responseDto.setId(payment.getId());
        responseDto.setStatus(String.valueOf(payment.getStatus()));
        responseDto.setType(String.valueOf(payment.getType()));
        responseDto.setRental(payment.getRental());
        responseDto.setUrl(payment.getUrl());
        responseDto.setSessionId(payment.getSessionId());
        responseDto.setPaymentAmount(payment.getPaymentAmount());
        return responseDto;
    }

    @Override
    public Payment toModel(PaymentRequestDto dto) {
        Payment payment = new Payment();
        payment.setStatus(Payment.Status.valueOf(dto.getStatus()));
        payment.setType(Payment.Type.valueOf(dto.getType()));
        payment.setRental(dto.getRental());
        payment.setSessionId(dto.getSessionId());
        payment.setUrl(dto.getUrl());
        payment.setPaymentAmount(dto.getPaymentAmount());
        return payment;
    }
}
