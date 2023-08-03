package com.example.carsharingservice.service.mapper;

import com.example.carsharingservice.config.MapperConfig;
import com.example.carsharingservice.dto.request.CarRequestDto;
import com.example.carsharingservice.dto.response.CarResponseDto;
import com.example.carsharingservice.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CarMapper extends DtoMapper<CarRequestDto, CarResponseDto, Car> {
    @Override
    @Mapping(source = "carType", target = "type")
    CarResponseDto toDto(Car car);

    @Override
    @Mapping(source = "type", target = "carType")
    Car toModel(CarRequestDto dto);
}
