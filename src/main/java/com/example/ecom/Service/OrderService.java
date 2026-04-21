package com.example.ecom.Service;

import com.example.ecom.DTO.OrderItemResponse;
import com.example.ecom.DTO.OrderResponse;
import com.example.ecom.Model.Cart;
import com.example.ecom.Model.Order;
import com.example.ecom.Model.OrderItem;
import com.example.ecom.Repository.CartItemRepository;
import com.example.ecom.Repository.OrderRepository;
import com.example.ecom.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartItemRepository cartRepo;
    private final OrderRepository orderRepo;
    private final UserRepository userRepo;

    public Order placeOrder(Long userId, Order orderRequest) {

        List<Cart> cartItems = cartRepo.findByUserId(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double total = cartItems.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        List<OrderItem> orderItems = cartItems.stream().map(c -> {
            OrderItem o = new OrderItem();
            o.setProductId(c.getProductId());
            o.setName(c.getName());
            o.setPrice(c.getPrice());
            o.setQuantity(c.getQuantity());
            return o;
        }).toList();

        Order order = new Order();
        order.setUser(userRepo.findById(userId).orElseThrow());
        order.setItems(orderItems);
        order.setTotalAmount(total);
        order.setAddress(orderRequest.getAddress());
        order.setName(orderRequest.getName());
        order.setPhone(orderRequest.getPhone());

        // IMPORTANT RELATION FIX
        orderItems.forEach(i -> i.setOrder(order));

        Order savedOrder = orderRepo.save(order);  // removed bad (Order) cast

        cartRepo.deleteAll(cartItems);

        return savedOrder;
    }

    public List<OrderResponse> getUserOrders(Long userId) {

        List<Order> orders = orderRepo.findByUserId(userId);

        return orders.stream().map(order -> {

            OrderResponse res = new OrderResponse();
            res.setId(order.getId());
            res.setTotal(order.getTotalAmount());
            res.setName(order.getName());
            res.setAddress(order.getAddress());
            res.setPhone(order.getPhone());

            List<OrderItemResponse> items = order.getItems().stream().map(i -> {
                OrderItemResponse item = new OrderItemResponse();
                item.setProductId(i.getProductId());
                item.setName(i.getName());
                item.setPrice(i.getPrice());
                item.setQuantity(i.getQuantity());
                return item;
            }).toList();

            res.setItems(items);

            return res;

        }).toList();
    }
}