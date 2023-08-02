package com.example.carsharingservice.service;

import com.example.carsharingservice.model.Car;
import java.util.List;

public interface CarService {
    Car save(Car car);

    Car get(Long id);

    List<Car> getAll();

    void delete(Long id);

    void update(Car car);
}
