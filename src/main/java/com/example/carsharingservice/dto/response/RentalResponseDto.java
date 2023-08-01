package com.example.carsharingservice.dto.response;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.model.User;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalResponseDto {
    private Long id;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private LocalDateTime actualReturnDate;
    private Car car;
    private User user;
}
