package com.example.carsharingservice.controller;

import com.example.carsharingservice.dto.request.RentalRequestDto;
import com.example.carsharingservice.dto.response.RentalResponseDto;
import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.model.Rental;
import com.example.carsharingservice.service.CarService;
import com.example.carsharingservice.service.RentalService;
import com.example.carsharingservice.service.TelegramNotificationService;
import com.example.carsharingservice.service.mapper.RentalMapper;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
@AllArgsConstructor
public class RentalController {
    private final RentalService rentalService;
    private final RentalMapper mapper;
    private CarService carService;
    private TelegramNotificationService notificationService;

    @PostMapping
    public RentalResponseDto addRental(@RequestBody @Valid RentalRequestDto rentalRequestDto) {
        Rental rental = mapper.toModel(rentalRequestDto);
        final Rental savedRental = rentalService.save(rental);
        notificationService.sendMessageToUser("Hello. New rental:"
                + savedRental.toString(), savedRental.getUser());
        return mapper.toDto(savedRental);
    }

    @PostMapping("/{id}/return")
    public RentalResponseDto returnRental(@PathVariable Long id) {
        Rental rental = rentalService.returnRental(id);
        Car car = rental.getCar();
        car.setInventory(car.getInventory() + 1);
        carService.save(car);
        notificationService.sendMessageToUser("Hello. Your rental:"
                + rental.toString(), rental.getUser());
        return mapper.toDto(rental);
    }

    @GetMapping
    public List<RentalResponseDto> getRentalsByUserIdAndIsActive(
            @RequestParam(value = "user_id", required = false) Long userId,
            @RequestParam(value = "is_active", required = false) boolean isActive) {
        List<Rental> rentals = rentalService.getRentalsByUserIdAndIsActive(userId, isActive);
        return rentals.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RentalResponseDto getRentalById(@PathVariable Long id) {
        Rental rental = rentalService.get(id);
        return mapper.toDto(rental);
    }
}
