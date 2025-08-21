package com.example.mvecommerce.controller;

import com.example.mvecommerce.domain.Cart;
import com.example.mvecommerce.dto.CartDtos.AddItemRequest;
import com.example.mvecommerce.repo.CartRepository;
import com.example.mvecommerce.security.User;
import com.example.mvecommerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final CartRepository cartRepository;

    public CartController(CartService cartService, CartRepository cartRepository) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
    }

    // NOTE: Replace with actual authenticated user after wiring security/JWT
    private User mockUser() {
        User u = new User();
        u.setId(1L);
        return u;
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> add(@RequestBody AddItemRequest req) {
        var user = mockUser();
        var cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart c = new Cart();
            c.setUser(user);
            return cartRepository.save(c);
        });
        return ResponseEntity.ok(cartService.addItem(cart, req));
    }
}
