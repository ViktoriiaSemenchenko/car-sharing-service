package com.example.carsharingservice.service.mapper;

import com.example.carsharingservice.config.MapperConfig;
import com.example.carsharingservice.dto.request.RentalRequestDto;
import com.example.carsharingservice.dto.response.RentalResponseDto;
import com.example.carsharingservice.model.Rental;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, uses = {CarMapper.class, UserMapper.class})
public interface RentalMapper extends DtoMapper<RentalRequestDto, RentalResponseDto, Rental> {
    @Override
    RentalResponseDto toDto(Rental rental);

    @Override
    Rental toModel(RentalRequestDto dto);
}
