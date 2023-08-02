package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.repository.CarRepository;
import com.example.carsharingservice.service.impl.CarServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CarServiceImplTest {
    private static CarRepository carRepository;
    private static CarServiceImpl carService;

    @BeforeAll
    static void beforeAll() {
        carRepository = Mockito.mock(CarRepository.class);
        carService = new CarServiceImpl(carRepository);
    }

    @Test
    void testUpdateCar() {
        Long cardId = 1L;
        Car car = new Car();
        car.setId(1L);
        car.setCarType(Car.CarType.SUV);
        car.setDailyFee(new BigDecimal(123));
        car.setBrand("BMW");
        car.setModel("X5");

        when(carRepository.findById(cardId)).thenReturn(Optional.of(car));
        when(carRepository.save(any(Car.class))).thenReturn(car);

        Car carUpdated = new Car();
        carUpdated.setId(cardId);
        carUpdated.setCarType(Car.CarType.SEDAN);
        carUpdated.setDailyFee(new BigDecimal("100.0"));
        carUpdated.setBrand("Audi");
        carUpdated.setModel("A4");

        carService.update(carUpdated);

        verify(carRepository, times(1)).findById(cardId);
        verify(carRepository, times(1)).save(car);

        assertEquals(Car.CarType.SEDAN, car.getCarType());
        assertEquals(new BigDecimal("100.0"), car.getDailyFee());
        assertEquals("Audi", car.getBrand());
        assertEquals("A4", car.getModel());
    }

    @Test
    void deleteCarTest() {
        Long carId = 1L;
        doNothing().when(carRepository).deleteById(carId);

        carService.delete(carId);

        verify(carRepository, times(1)).deleteById(carId);
    }

    @Test
    public void testGetAllCars() {
        List<Car> cars = new ArrayList<>();

        Car car1 = new Car();
        car1.setId(1L);
        car1.setCarType(Car.CarType.SUV);
        car1.setDailyFee(new BigDecimal("50.0"));
        car1.setBrand("Toyota");
        car1.setModel("Rav4");
        cars.add(car1);

        Car car2 = new Car();
        car2.setId(2L);
        car2.setCarType(Car.CarType.SEDAN);
        car2.setDailyFee(new BigDecimal("40.0"));
        car2.setBrand("Honda");
        car2.setModel("Civic");
        cars.add(car2);

        when(carRepository.findAll()).thenReturn(cars);

        List<Car> allCars = carService.getAll();

        assertNotNull(allCars);
        assertEquals(2, allCars.size());
        assertEquals(Car.CarType.SUV, allCars.get(0).getCarType());
        assertEquals(new BigDecimal("50.0"), allCars.get(0).getDailyFee());
        assertEquals("Toyota", allCars.get(0).getBrand());
        assertEquals("Rav4", allCars.get(0).getModel());

        assertEquals(Car.CarType.SEDAN, allCars.get(1).getCarType());
        assertEquals(new BigDecimal("40.0"), allCars.get(1).getDailyFee());
        assertEquals("Honda", allCars.get(1).getBrand());
        assertEquals("Civic", allCars.get(1).getModel());

        verify(carRepository, times(1)).findAll();
    }
}
