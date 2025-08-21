package com.example.mvecommerce.dto;

import java.math.BigDecimal;

public class ProductDtos {
    public record ProductRequest(String title, String description, Long vendorId, Long categoryId, BigDecimal price, Integer stock, String imageUrl){}
}