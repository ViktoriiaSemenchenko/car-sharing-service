package com.example.carsharingservice.service;

import java.time.LocalDateTime;
import java.util.List;
import com.example.carsharingservice.model.Rental;

public interface RentalService {
    Rental save(Rental rental);

    Rental get(Long id);

    Rental setActualReturnDate(Long rentalId, LocalDateTime actualReturnDate);

    Rental returnRental(Long id);


    List<Rental> getRentalsByUserIdAndIsActive(Long userId, boolean isActive);
}
