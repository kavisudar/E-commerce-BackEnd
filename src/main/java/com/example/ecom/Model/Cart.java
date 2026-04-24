package com.example.ecom.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    private String name;
    private Double price;
    private int quantity;

    @Column(name = "user_id")
    private Long userId;
}