package com.example.carsharingservice.service.impl;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.repository.CarRepository;
import com.example.carsharingservice.service.CarService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car get(Long id) {
        return carRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can't find car by id " + id));
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void update(Car car) {
        Optional<Car> optionalCar = carRepository.findById(car.getId());
        if (optionalCar.isEmpty()) {
            throw new NoSuchElementException("Can't find car by id " + car);
        }
        Car carToUpdate = optionalCar.get();
        carToUpdate.setCarType(car.getCarType());
        carToUpdate.setDailyFee(car.getDailyFee());
        carToUpdate.setBrand(car.getCarType().toString());
        carToUpdate.setModel(car.getModel());
        carRepository.save(carToUpdate);
    }
}
