package com.example.mvecommerce.service;

import com.example.mvecommerce.domain.*;
import com.example.mvecommerce.repo.*;
import com.example.mvecommerce.dto.CartDtos.AddItemRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Cart addItem(Cart cart, AddItemRequest req) {
        var product = productRepository.findById(req.productId()).orElseThrow();
        var item = CartItem.builder().cart(cart).product(product).quantity(req.quantity()).build();
        cartItemRepository.save(item);
        return cartRepository.findById(cart.getId()).orElseThrow();
    }
}
