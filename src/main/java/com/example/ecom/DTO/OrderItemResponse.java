package com.example.ecom.DTO;

import lombok.Data;

@Data
public class OrderItemResponse {
    private Long productId;
    private String name;
    private Double price;
    private int quantity;
}
