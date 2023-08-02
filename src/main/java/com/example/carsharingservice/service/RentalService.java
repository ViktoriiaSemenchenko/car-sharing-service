package com.example.carsharingservice.service;

import com.example.carsharingservice.model.Rental;
import java.time.LocalDateTime;
import java.util.List;

public interface RentalService {
    Rental save(Rental rental);

    Rental get(Long id);

    Rental setActualReturnDate(Long rentalId, LocalDateTime actualReturnDate);

    Rental returnRental(Long id);

    List<Rental> getRentalsByUserIdAndIsActive(Long userId, boolean isActive);
}
