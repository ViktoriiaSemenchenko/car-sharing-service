package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.example.carsharingservice.model.Payment;
import com.example.carsharingservice.model.Rental;
import com.example.carsharingservice.repository.PaymentRepository;
import com.example.carsharingservice.service.PaymentHandler;
import com.example.carsharingservice.service.RentalService;
import com.example.carsharingservice.service.impl.PaymentHandlerStrategy;
import com.example.carsharingservice.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PaymentServiceImplTest {
    @Mock
    private RentalService rentalService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentHandlerStrategy paymentHandlerStrategy;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPaymentByUserId() {
        long userId = 1L;
        List<Rental> rentals = new ArrayList<>();
        Rental rental1 = new Rental();
        rental1.setId(1L);
        Rental rental2 = new Rental();
        rental2.setId(2L);
        rentals.add(rental1);
        rentals.add(rental2);

        when(rentalService.getRentalsByUserIdAndIsActive(userId, false)).thenReturn(rentals);

        when(paymentRepository.findByRentalId(anyLong())).thenReturn(new Payment());

        List<Payment> payments = paymentService.getPaymentsByUserId(userId);

        assertNotNull(payments);
        assertEquals(rentals.size(), payments.size());

        verify(rentalService, times(1)).
                getRentalsByUserIdAndIsActive(userId, false);
        verify(paymentRepository, times(rentals.size())).findByRentalId(anyLong());
    }

    @Test
    void calculatePayment() {
        long rentalId = 1L;
        Rental rental = new Rental();
        rental.setId(rentalId);
        PaymentHandler mockHandler = mock(PaymentHandler.class);

        when(rentalService.get(rentalId)).thenReturn(rental);

        when(paymentHandlerStrategy.getHandler(any(Payment.Type.class))).thenReturn(mockHandler);

        when(mockHandler.calculateTotalAmount(rental)).thenReturn(BigDecimal.TEN);

        BigDecimal result = paymentService.calculatePaymentAmount(rentalId, Payment.Type.FINE);

        assertEquals(BigDecimal.valueOf(10).multiply(BigDecimal.valueOf(100)), result);
    }

    @Test
    void isSessionPaid() {
        Payment payment = mock(Payment.class);
        when(payment.getStatus()).thenReturn(Payment.Status.PAID);

        when(paymentRepository.findBySessionId("test")).thenReturn(payment);
        assertTrue(paymentService.isSessionPaid("test"));
    }
}

