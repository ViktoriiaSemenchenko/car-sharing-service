package com.example.carsharingservice.service.mapper;

import com.example.carsharingservice.config.MapperConfig;
import com.example.carsharingservice.dto.request.PaymentRequestDto;
import com.example.carsharingservice.dto.response.PaymentResponseDto;
import com.example.carsharingservice.model.Payment;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, uses = {RentalMapper.class})
public interface PaymentMapper extends DtoMapper<PaymentRequestDto, PaymentResponseDto, Payment> {
    @Override
    PaymentResponseDto toDto(Payment payment);

    @Override
    Payment toModel(PaymentRequestDto dto);
}
