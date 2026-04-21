package com.example.ecom.Controller;

import com.example.ecom.DTO.OrderResponse;
import com.example.ecom.Model.Order;
import com.example.ecom.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //  PLACE ORDER
    @PostMapping("/place/{userId}")
    public Order placeOrder(
            @PathVariable Long userId,
            @RequestBody Order orderRequest
    ) {
        return orderService.placeOrder(userId, orderRequest);
    }

    //  GET USER ORDERS
    @GetMapping("/{userId}")
    public List<OrderResponse> getOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }

    @PutMapping("/cancel/{orderId}")
    public Order cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);
    }


}
