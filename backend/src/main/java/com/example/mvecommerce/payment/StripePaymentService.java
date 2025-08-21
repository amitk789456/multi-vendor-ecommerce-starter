package com.example.mvecommerce.payment;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.RefundCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class StripePaymentService implements PaymentService {
    public StripePaymentService(@Value("${STRIPE_API_KEY:}") String apiKey) {
        Stripe.apiKey = apiKey;
    }

    @Override
    public PaymentResult createPayment(BigDecimal amount, String currency, String description, Map<String, Object> metadata) {
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount.multiply(new BigDecimal(100)).longValue())
                .setCurrency(currency)
                .setDescription(description)
                .putAllMetadata((Map)metadata)
                .build();
            PaymentIntent intent = PaymentIntent.create(params);
            return new PaymentResult(true, "stripe", intent.getId(), intent.toJson());
        } catch (Exception e) {
            return new PaymentResult(false, "stripe", null, e.getMessage());
        }
    }

    @Override
    public PaymentResult refund(String paymentReference, BigDecimal amount) {
        try {
            RefundCreateParams params = RefundCreateParams.builder()
                .setPaymentIntent(paymentReference)
                .setAmount(amount.multiply(new BigDecimal(100)).longValue())
                .build();
            Refund refund = Refund.create(params);
            return new PaymentResult(true, "stripe", refund.getId(), refund.toJson());
        } catch (Exception e) {
            return new PaymentResult(false, "stripe", null, e.getMessage());
        }
    }
}
