package com.example.carsharingservice.service.mapper;

import com.example.carsharingservice.dto.request.RentalRequestDto;
import com.example.carsharingservice.dto.response.RentalResponseDto;
import com.example.carsharingservice.model.Rental;
import com.example.carsharingservice.service.CarService;
import com.example.carsharingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper implements DtoMapper<RentalRequestDto, RentalResponseDto, Rental> {
    private final CarService carService;
    private final UserService userService;

    @Autowired
    public RentalMapper(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @Override
    public RentalResponseDto toDto(Rental rental) {
        RentalResponseDto responseDto = new RentalResponseDto();
        responseDto.setId(rental.getId());
        responseDto.setRentalDate(rental.getRentalDate());
        responseDto.setReturnDate(rental.getReturnDate());
        responseDto.setActualReturnDate(rental.getActualReturnDate());
        responseDto.setCarId(rental.getCar().getId());
        responseDto.setUserId(rental.getUser().getId());
        return responseDto;
    }

    @Override
    public Rental toModel(RentalRequestDto dto) {
        Rental rental = new Rental();
        rental.setRentalDate(dto.getRentalDate());
        rental.setReturnDate(dto.getReturnDate());
        rental.setActualReturnDate(dto.getActualReturnDate());
        rental.setCar(carService.get(dto.getCarId()));
        rental.setUser(userService.getById(dto.getUserId()));
        return rental;
    }
}
