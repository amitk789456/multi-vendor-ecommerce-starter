package com.example.mvecommerce.controller;

import com.example.mvecommerce.domain.*;
import com.example.mvecommerce.payment.PaymentService;
import com.example.mvecommerce.repo.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    public OrderController(OrderRepository orderRepository, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<Map<String,Object>> pay(@RequestParam BigDecimal amount, @RequestParam(defaultValue="INR") String currency) {
        var res = paymentService.createPayment(amount, currency, "Order Payment", Map.of("source","api"));
        return ResponseEntity.ok(Map.of("success", res.success(), "provider", res.provider(), "reference", res.reference(), "raw", res.raw()));
    }

    @GetMapping
    public List<Order> list() { return orderRepository.findAll(); }
}
