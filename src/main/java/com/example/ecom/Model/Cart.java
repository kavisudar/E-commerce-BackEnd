package com.example.ecom.Model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String name;
    private Double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
