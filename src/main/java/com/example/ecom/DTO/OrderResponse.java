package com.example.ecom.DTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private Double total;
    private String name;
    private String address;
    private String phone;
    private String status;

    private List<OrderItemResponse> items;
}
