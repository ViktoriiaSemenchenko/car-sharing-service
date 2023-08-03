package com.example.carsharingservice.dto.request;

import jakarta.validation.constraints.NotNull;
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
    private Long carId;
    private Long userId;
}
