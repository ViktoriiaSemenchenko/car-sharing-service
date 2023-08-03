package com.example.carsharingservice.controller;

import com.example.carsharingservice.dto.request.PaymentRequestInfoDto;
import com.example.carsharingservice.dto.response.PaymentResponseDto;
import com.example.carsharingservice.model.Payment;
import com.example.carsharingservice.service.PaymentService;
import com.example.carsharingservice.service.StripeService;
import com.example.carsharingservice.service.TelegramNotificationService;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final StripeService stripeService;
    private final PaymentService paymentService;
    private final TelegramNotificationService notificationService;

    @PostMapping
    public PaymentResponseDto createStripeSession(
            @RequestBody PaymentRequestInfoDto paymentInfoRequestDto) {
        SessionCreateParams params = stripeService.createPaymentSession(
                paymentInfoRequestDto.getRentalId(), paymentInfoRequestDto.getType());
        return stripeService.getPaymentFromSession(params, paymentInfoRequestDto);
    }

    @GetMapping("/success")
    public String success(@RequestParam("session_id") String sessionId) {
        Payment payment = paymentService.findBySessionId(sessionId);
        if (payment == null) {
            notificationService.sendMessageToUser("Payment not found.",
                    paymentService.findBySessionId(sessionId).getRental().getUser());
            return "payment not found";
        }
        if (paymentService.isSessionPaid(sessionId)) {
            notificationService.sendMessageToUser("Payment was not successful.",
                    paymentService.findBySessionId(sessionId).getRental().getUser());
            return "invalid payment";
        }

        payment.setStatus(Payment.Status.PAID);
        paymentService.update(payment);
        notificationService.sendMessageToUser("Payment successfully processed.",
                paymentService.findBySessionId(sessionId).getRental().getUser());
        return "Your payment was successful!";
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> returnPaymentPausedMessage() {
        return ResponseEntity.ok("Payment can be made later. "
                + "The session is available for 23 hours.");
    }
}
