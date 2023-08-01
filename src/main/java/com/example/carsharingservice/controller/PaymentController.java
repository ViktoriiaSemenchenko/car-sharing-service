package com.example.carsharingservice.controller;

import com.example.carsharingservice.dto.request.PaymentRequestDto;
import com.example.carsharingservice.service.PaymentService;
import com.example.carsharingservice.service.RentalService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final RentalService rentalService;

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    public PaymentController(PaymentService paymentService, RentalService rentalService) {
        this.paymentService = paymentService;
        this.rentalService = rentalService;
    }

    @PostMapping("/create-payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentRequestDto createPayment)
            throws StripeException {
        try {
            Stripe.apiKey = stripeSecretKey;
            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setCurrency("usd")
                    .setAmount(15 * 100L)
                    .build();
            PaymentIntent intent = PaymentIntent.create(createParams);
            return ResponseEntity.ok(intent.getClientSecret());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Помилка створення платіжного інтенту.");
        }
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
