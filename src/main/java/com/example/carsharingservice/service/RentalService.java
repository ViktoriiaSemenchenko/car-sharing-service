package com.example.carsharingservice.service;

import com.example.carsharingservice.model.Rental;
import java.time.LocalDate;
import java.util.List;

public interface RentalService {
    Rental save(Rental rental);

    Rental get(Long id);

    Rental setActualReturnDate(Long rentalId, LocalDate actualReturnDate);

    List<Rental> getRentalsByUserIdAndIsActive(Long userId, boolean isActive);
}
