package com.example.carsharingservice.service.impl;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.model.Rental;
import com.example.carsharingservice.model.User;
import com.example.carsharingservice.repository.RentalRepository;
import com.example.carsharingservice.service.CarService;
import com.example.carsharingservice.service.RentalService;
import com.example.carsharingservice.service.TelegramNotificationService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final CarService carService;
    private final TelegramNotificationService notificationService;

    @Override
    public Rental save(Rental rental) {
        Car car = rental.getCar();
        if (car.getInventory() == 0) {
            throw new NoSuchElementException("This car is currently"
                    + " unavailable for rental.");
        }
        car.setInventory(car.getInventory() - 1);
        carService.update(car);
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
    public Rental returnRental(Long id) {
        Rental rental = get(id);
        rental.setActualReturnDate(LocalDateTime.now());
        return rentalRepository.save(rental);
    }

    @Override
    public List<Rental> getRentalsByUserIdAndIsActive(Long userId, boolean isActive) {
        if (isActive) {
            return rentalRepository.findByUserIdAndActualReturnDateIsNull(userId);
        } else {
            return rentalRepository.findByUserIdAndActualReturnDateIsNotNull(userId);
        }
    }

    @Scheduled(cron = "0 38 22 * * ?")
    public void checkOverdueRentals() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        List<Rental> overdueRentals = rentalRepository.findOverdueRentalsForTomorrow(tomorrow);
        if (!overdueRentals.isEmpty()) {
            for (Rental rental : overdueRentals) {
                String message = "Overdue rental: Rental ID - " + rental.getId() + ", Return Date - " + rental.getReturnDate();
                notificationService.sendMessageToUser(message, rental.getUser());
            }
        } else {
            notificationService.sendMessageToUser("No rentals overdue today!", new User());
        }
    }
}
