package com.example.ecom.Controller;

import com.example.ecom.Model.Cart;
import com.example.ecom.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
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

    @PutMapping("/increase/{cartId}")
    public Cart increaseQty(@PathVariable Long cartId) {
        return cartService.increaseQuantity(cartId);
    }

    @PutMapping("/decrease/{cartId}")
    public Cart decreaseQty(@PathVariable Long cartId) {
        return cartService.decreaseQuantity(cartId);
    }

    @DeleteMapping("/delete/{cartId}")
    public void deleteCartItem(@PathVariable Long cartId) {
        cartService.deleteCartItem(cartId);
    }

}

