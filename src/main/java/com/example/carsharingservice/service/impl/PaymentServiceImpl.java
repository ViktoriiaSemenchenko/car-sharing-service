package com.example.carsharingservice.service.impl;

import com.example.carsharingservice.model.Payment;
import com.example.carsharingservice.repository.PaymentRepository;
import com.example.carsharingservice.service.PaymentService;
import com.example.carsharingservice.service.RentalService;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final RentalService rentalService;

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can't find payment id: " + id));
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<Payment> getPaymentsByUserId(Long userId) {
        return rentalService.getRentalsByUserIdAndIsActive(userId).stream()
                .map(rental -> paymentRepository.findByRentalId(rental.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal calculatePaymentAmount(Long rentalId, Payment.Type type) {
        return null;
    }

    @Override
    public boolean isSessionPaid(String sessionId) {
        return paymentRepository.findBySessionId(sessionId)
                .getStatus()
                .equals(Payment.Status.PAID);
    }

    @Override
    public Payment findBySessionId(String sessionId) {
        return paymentRepository.findBySessionId(sessionId);
    }
}
