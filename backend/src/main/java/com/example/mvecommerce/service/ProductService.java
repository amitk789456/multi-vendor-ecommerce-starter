package com.example.mvecommerce.service;

import com.example.mvecommerce.domain.*;
import com.example.mvecommerce.repo.*;
import com.example.mvecommerce.dto.ProductDtos.ProductRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    public List<Product> listAll() { return productRepository.findAll(); }

    public Product get(Long id) { return productRepository.findById(id).orElseThrow(); }

    @Transactional
    public Product create(ProductRequest req) {
        var vendor = vendorRepository.findById(req.vendorId()).orElseThrow();
        var category = categoryRepository.findById(req.categoryId()).orElseThrow();
        var p = Product.builder()
            .title(req.title())
            .description(req.description())
            .vendor(vendor)
            .category(category)
            .price(req.price())
            .stock(req.stock())
            .imageUrl(req.imageUrl())
            .build();
        return productRepository.save(p);
    }
}
