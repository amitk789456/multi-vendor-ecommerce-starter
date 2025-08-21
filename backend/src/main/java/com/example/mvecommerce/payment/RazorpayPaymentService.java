package com.example.mvecommerce.payment;

import com.razorpay.RazorpayClient;
import com.razorpay.Payment;
import com.razorpay.Refund;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class RazorpayPaymentService implements PaymentService {
    private final RazorpayClient client;
    public RazorpayPaymentService(
            @Value("${RAZORPAY_KEY_ID:}") String key,
            @Value("${RAZORPAY_KEY_SECRET:}") String secret) throws Exception {
        this.client = (key == null || key.isBlank()) ? null : new RazorpayClient(key, secret);
    }

    @Override
    public PaymentResult createPayment(BigDecimal amount, String currency, String description, Map<String, Object> metadata) {
        try {
            if (client == null) return new PaymentResult(false, "razorpay", null, "Client not configured");
            JSONObject options = new JSONObject();
            options.put("amount", amount.multiply(new BigDecimal(100)).longValue());
            options.put("currency", currency);
            options.put("receipt", description);
            metadata.forEach(options::put);
            Payment payment = client.Payments.create(options);
            return new PaymentResult(true, "razorpay", payment.get("id"), payment.toString());
        } catch (Exception e) {
            return new PaymentResult(false, "razorpay", null, e.getMessage());
        }
    }

    @Override
    public PaymentResult refund(String paymentReference, BigDecimal amount) {
        try {
            if (client == null) return new PaymentResult(false, "razorpay", null, "Client not configured");
            JSONObject options = new JSONObject();
            options.put("payment_id", paymentReference);
            options.put("amount", amount.multiply(new BigDecimal(100)).longValue());
            Refund refund = client.Payments.refund(options);
            return new PaymentResult(true, "razorpay", refund.get("id"), refund.toString());
        } catch (Exception e) {
            return new PaymentResult(false, "razorpay", null, e.getMessage());
        }
    }
}
