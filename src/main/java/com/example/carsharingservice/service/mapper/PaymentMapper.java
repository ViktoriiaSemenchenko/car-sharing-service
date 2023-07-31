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
        responseDto.setRentalId(payment.getRentalId());
        responseDto.setSessionUrl(payment.getSessionUrl());
        responseDto.setSessionId(payment.getSessionId());
        responseDto.setAmountToPay(payment.getAmountToPay());
        return responseDto;
    }

    @Override
    public Payment toModel(PaymentRequestDto dto) {
        Payment payment = new Payment();
        payment.setStatus(Payment.Status.valueOf(dto.getStatus()));
        payment.setType(Payment.Type.valueOf(dto.getType()));
        payment.setRentalId(dto.getRentalId());
        payment.setSessionUrl(dto.getSessionUrl());
        payment.setSessionId(dto.getSessionId());
        payment.setAmountToPay(dto.getAmountToPay());
        return payment;
    }
}
