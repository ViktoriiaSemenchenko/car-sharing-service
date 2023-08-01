package com.example.carsharingservice.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import com.example.carsharingservice.dto.request.RentalRequestDto;
import com.example.carsharingservice.dto.response.RentalResponseDto;
import com.example.carsharingservice.model.Rental;
import com.example.carsharingservice.service.RentalService;
import com.example.carsharingservice.service.mapper.DtoMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    private final DtoMapper<RentalRequestDto, RentalResponseDto, Rental> mapper;

    @PostMapping
    public RentalResponseDto addRental(@RequestBody @Valid RentalRequestDto rentalRequestDto) {
        Rental rental = rentalService.save(mapper.toModel(rentalRequestDto));
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
    public RentalResponseDto getRentalById(@PathVariable("id") Long rentalId) {
        return mapper.toDto(rentalService.get(rentalId));
    }

    @PostMapping("/{id}/return")
    public RentalResponseDto setActualReturnDate(
            @PathVariable("id") Long id,
            @RequestParam("actual_return_date")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate actualReturnDate) {
        Rental rental = rentalService.setActualReturnDate(id, actualReturnDate);
        return mapper.toDto(rental);
    }
}
