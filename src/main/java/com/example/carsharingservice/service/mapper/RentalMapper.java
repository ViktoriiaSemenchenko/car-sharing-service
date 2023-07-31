package com.example.carsharingservice.service.mapper;

import com.example.carsharingservice.dto.request.RentalRequestDto;
import com.example.carsharingservice.dto.response.RentalResponseDto;
import com.example.carsharingservice.model.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper implements DtoMapper<RentalRequestDto, RentalResponseDto, Rental> {
    @Override
    public RentalResponseDto toDto(Rental rental) {
        RentalResponseDto responseDto = new RentalResponseDto();
        responseDto.setId(rental.getId());
        responseDto.setRentalDate(rental.getRentalDate());
        responseDto.setReturnDate(rental.getReturnDate());
        responseDto.setActualReturnDate(rental.getActualReturnDate());
        responseDto.setCarId(rental.getCarId());
        responseDto.setUserId(rental.getUserId());
        return responseDto;
    }

    @Override
    public Rental toModel(RentalRequestDto dto) {
        Rental rental = new Rental();
        rental.setRentalDate(dto.getRentalDate());
        rental.setReturnDate(dto.getReturnDate());
        rental.setActualReturnDate(dto.getActualReturnDate());
        rental.setCarId(dto.getCarId());
        rental.setUserId(dto.getUserId());
        return rental;
    }
}
