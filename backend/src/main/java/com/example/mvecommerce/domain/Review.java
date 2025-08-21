package com.example.mvecommerce.domain;

import com.example.mvecommerce.common.Auditable;
import com.example.mvecommerce.security.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Review extends Auditable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    private User author;

    @Column(nullable = false)
    private int rating; // 1-5

    @Column(length = 1000)
    private String comment;
}
