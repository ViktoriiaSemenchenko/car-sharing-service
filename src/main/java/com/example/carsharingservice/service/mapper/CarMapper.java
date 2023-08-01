package com.example.carsharingservice.service.mapper;

import com.example.carsharingservice.dto.request.CarRequestDto;
import com.example.carsharingservice.dto.response.CarResponseDto;
import com.example.carsharingservice.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements DtoMapper<CarRequestDto, CarResponseDto, Car> {
    @Override
    public CarResponseDto toDto(Car car) {
        CarResponseDto responseDto = new CarResponseDto();
        responseDto.setId(car.getId());
        responseDto.setModel(car.getModel());
        responseDto.setBrand(car.getBrand());
        responseDto.setInventory(car.getInventory());
        responseDto.setDailyFee(car.getDailyFee());
        responseDto.setType(String.valueOf(car.getCarType()));
        return responseDto;
    }

    @Override
    public Car toModel(CarRequestDto dto) {
        Car car = new Car();
        car.setModel(dto.getModel());
        car.setBrand(dto.getBrand());
        car.setInventory(dto.getInventory());
        car.setDailyFee(dto.getDailyFee());
        car.setCarType(Car.CarType.valueOf(dto.getType()));
        return car;
    }
}
