package com.example.mvecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MultiVendorEcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiVendorEcommerceApplication.class, args);
    }
}
