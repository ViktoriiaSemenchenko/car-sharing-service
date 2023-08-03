package service;

import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.model.Rental;
import com.example.carsharingservice.repository.RentalRepository;
import com.example.carsharingservice.service.CarService;
import com.example.carsharingservice.service.impl.RentalServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RentalServiceImplTest {
    @Mock
    private static RentalRepository rentalRepository;

    @Mock
    private static CarService carService;

    @InjectMocks
    private RentalServiceImpl rentalService;

    private static Car availableCar;
    private static Car unavailableCar;

    @BeforeAll
    static void beforeAll() {
        availableCar = new Car();
        availableCar.setInventory(1);

        unavailableCar = new Car();
        unavailableCar.setInventory(0);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveRentalWithAvailableCar() {
        Rental rental = new Rental();
        rental.setId(1L);
        rental.setCar(availableCar);

        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);

        Rental savedRental = rentalService.save(rental);

        assertNotNull(savedRental);
        assertEquals(0, availableCar.getInventory());
        assertEquals(rental, savedRental);

        verify(carService, times(1)).update(availableCar);
        verify(rentalRepository, times(1)).save(rental);
    }

    @Test
    void testSaveRentalWithUnavailableCar() {
        Rental rental = new Rental();
        rental.setId(1L);
        rental.setCar(unavailableCar);

        assertThrows(NoSuchElementException.class, () -> rentalService.save(rental));

        verify(carService, never()).update(unavailableCar);
        verify(rentalRepository, never()).save(rental);
    }
}
