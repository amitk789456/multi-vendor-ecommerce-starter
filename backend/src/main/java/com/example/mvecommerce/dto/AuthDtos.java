package com.example.mvecommerce.dto;

public class AuthDtos {
    public record RegisterRequest(String name, String email, String password){}
    public record LoginRequest(String email, String password){}
    public record AuthResponse(String token){}
}