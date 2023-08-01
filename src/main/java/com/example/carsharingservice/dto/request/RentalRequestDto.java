package com.example.carsharingservice.dto.request;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.model.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalRequestDto {
    @NotNull
    private LocalDateTime rentalDate;
    @NotNull
    private LocalDateTime returnDate;
    @NotNull
    private LocalDateTime actualReturnDate;
    @Positive
    private Car car;
    @Positive
    private User user;
}
