package com.example.mvecommerce.payment;

import java.math.BigDecimal;
import java.util.Map;

public interface PaymentService {
    record PaymentResult(boolean success, String provider, String reference, String raw){}
    PaymentResult createPayment(BigDecimal amount, String currency, String description, Map<String, Object> metadata);
    PaymentResult refund(String paymentReference, BigDecimal amount);
}
