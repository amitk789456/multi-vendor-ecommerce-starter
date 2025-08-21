package com.example.mvecommerce.controller;

import com.example.mvecommerce.domain.Product;
import com.example.mvecommerce.dto.ProductDtos.ProductRequest;
import com.example.mvecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) { this.productService = productService; }

    @GetMapping
    public List<Product> list() { return productService.listAll(); }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) { return productService.get(id); }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDOR')")
    public ResponseEntity<Product> create(@RequestBody ProductRequest req) {
        return ResponseEntity.ok(productService.create(req));
    }
}
