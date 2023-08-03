package com.example.carsharingservice.config;

import org.mapstruct.NullValueCheckStrategy;

@org.mapstruct.MapperConfig(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "com.example.carsharingservice.service.mapper.impl"
)
public class MapperConfig {
}
