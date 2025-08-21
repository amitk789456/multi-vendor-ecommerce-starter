package com.example.mvecommerce.dto;

public class CartDtos {
    public record AddItemRequest(Long productId, int quantity){}
}