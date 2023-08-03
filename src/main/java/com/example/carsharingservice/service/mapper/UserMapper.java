package com.example.carsharingservice.service.mapper;

import com.example.carsharingservice.config.MapperConfig;
import com.example.carsharingservice.dto.request.UserRequestDto;
import com.example.carsharingservice.dto.response.UserResponseDto;
import com.example.carsharingservice.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper extends DtoMapper<UserRequestDto, UserResponseDto, User> {
    @Override
    UserResponseDto toDto(User user);

    @Override
    User toModel(UserRequestDto dto);
}
