package com.example.carsharingservice.service.impl;

import com.example.carsharingservice.dto.request.PaymentRequestDto;
import com.example.carsharingservice.dto.request.PaymentRequestInfoDto;
import com.example.carsharingservice.dto.response.PaymentResponseDto;
import com.example.carsharingservice.model.Payment;
import com.example.carsharingservice.service.PaymentService;
import com.example.carsharingservice.service.StripeService;
import com.example.carsharingservice.service.mapper.PaymentMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StripeServiceImpl implements StripeService {
    private static final String PAYMENT_URL = "http://localhost:8080/payments";
    @Value("${stripe.api.key}")
    private static String stripeSecretKey;
    private final PaymentService paymentService;
    private final PaymentMapper mapper;

    @Override
    public SessionCreateParams createPaymentSession(Long rentalId, Payment.Type type) {
        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams.Builder builder = new SessionCreateParams.Builder();
        builder.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD);
        builder.setMode(SessionCreateParams.Mode.PAYMENT);
        builder.setSuccessUrl(PAYMENT_URL + "/success" + "?session_id={CHECKOUT_SESSION_ID}");
        builder.setCancelUrl(PAYMENT_URL + "/cancel");
        builder.addLineItem(
                new SessionCreateParams.LineItem.Builder()
                        .setPriceData(
                                new SessionCreateParams.LineItem.PriceData.Builder()
                                        .setCurrency("usd")
                                        .setProductData(
                                                new SessionCreateParams.LineItem
                                                        .PriceData.ProductData.Builder()
                                                        .setName("Car Rental Payment")
                                                        .build()
                                        )
                                        .setUnitAmount(
                                                paymentService
                                                        .calculatePaymentAmount(rentalId, type)
                                                        .longValue())
                                        .build()
                        )
                        .setQuantity(1L)
                        .build()
        );

        return builder.build();
    }

    @Override
    public PaymentResponseDto getPaymentFromSession(SessionCreateParams params,
                                                    PaymentRequestInfoDto paymentRequestInfoDto) {
        try {
            Session session = Session.create(params);
            String sessionUrl = session.getCancelUrl();
            String sessionId = session.getId();
            BigDecimal amountToPay = new BigDecimal(session.getAmountTotal());
            PaymentRequestDto requestDto = new PaymentRequestDto();
            requestDto.setSessionId(sessionId);
            requestDto.setUrl(new URL(sessionUrl));
            requestDto.setType(String.valueOf(paymentRequestInfoDto.getType()));
            requestDto.setStatus(String.valueOf(Payment.Status.PENDING));
            requestDto.setPaymentAmount(amountToPay.divide(BigDecimal.valueOf(100),
                    RoundingMode.HALF_UP));
            return mapper.toDto(paymentService.save(mapper.toModel(requestDto)));
        } catch (StripeException | MalformedURLException e) {
            throw new RuntimeException("Can't get payment page.", e);
        }
    }
}
