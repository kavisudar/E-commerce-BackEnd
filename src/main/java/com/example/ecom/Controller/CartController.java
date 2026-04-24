package com.example.ecom.Controller;

import com.example.ecom.Model.Cart;
import com.example.ecom.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add/{userId}")
    public Cart addToCart(@PathVariable Long userId,
                          @RequestBody Cart item) {
        return cartService.addToCart(userId, item);
    }

    @GetMapping("/{userId}")
    public List<Cart> getCart(@PathVariable Long userId) {
        return cartService.getUserCart(userId);
    }

    @PutMapping("/increase/{id}")
    public ResponseEntity<?> increaseQty(@PathVariable Long id) {
        return cartService.increaseQty(id);
    }

    @PutMapping("/decrease/{id}")
    public ResponseEntity<?> decreaseQty(@PathVariable Long id) {
        return cartService.decreaseQty(id);
    }

    @DeleteMapping("/delete/{cartId}")
    public void deleteCartItem(@PathVariable Long cartId) {
        cartService.deleteCartItem(cartId);
    }
}