package com.example.carsharingservice.controller;

import com.example.carsharingservice.dto.request.PaymentRequestInfoDto;
import com.example.carsharingservice.dto.response.PaymentResponseDto;
import com.example.carsharingservice.service.PaymentService;
import com.example.carsharingservice.service.StripeService;
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

    @PostMapping
    public PaymentResponseDto createStripeSession(
            @RequestBody PaymentRequestInfoDto paymentInfoRequestDto) {
        SessionCreateParams params = stripeService.createPaymentSession(
                paymentInfoRequestDto.getRentalId(), paymentInfoRequestDto.getType());
        return stripeService.getPaymentFromSession(params, paymentInfoRequestDto);
    }

    @GetMapping("/success")
    public ResponseEntity<String> handleSuccessfulPayment(@RequestParam("sessionId")
                                                              String sessionId) {
        boolean paymentSuccessful = paymentService.isSessionPaid(sessionId);
        if (paymentSuccessful) {
            paymentService.getPaymentsByUserId(Long.valueOf(sessionId));
            return ResponseEntity.ok("Payment successfully processed.");
        } else {
            return ResponseEntity.badRequest().body("Payment was not successful.");
        }
    }


    // GET: payments/cancel/
    @GetMapping("/cancel/")
    public ResponseEntity<String> returnPaymentPausedMessage() {
        return ResponseEntity.ok("Payment can be made later. "
                + "The session is available for 24 hours.");
    }
}
