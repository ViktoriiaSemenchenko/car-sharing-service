package com.example.carsharingservice.service.impl;

import com.example.carsharingservice.model.Rental;
import com.example.carsharingservice.repository.RentalRepository;
import com.example.carsharingservice.service.RentalService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;

    @Override
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    @Override
    public Rental get(Long id) {
        return rentalRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can't find rental by id " + id));
    }

    @Override
    public Rental setActualReturnDate(Long rentalId, LocalDateTime actualReturnDate) {
        Rental rental = rentalRepository.findById(rentalId).orElse(null);
        if (rental != null) {
            rental.setActualReturnDate(actualReturnDate);
            return rentalRepository.save(rental);
        }
        return null;
    }

    @Override
    public List<Rental> getRentalsByUserIdAndIsActive(Long userId, boolean isActive) {
        if (isActive) {
            return rentalRepository.findByUserIdAndActualReturnDateIsNull(userId);
        } else {
            return rentalRepository.findByUserIdAndActualReturnDateIsNotNull(userId);
        }
    }
}
