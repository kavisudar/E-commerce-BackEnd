package com.example.ecom.Service;

import com.example.ecom.Model.Cart;
import com.example.ecom.Repository.CartItemRepository;
import com.example.ecom.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.ecom.Model.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartRepo;
    private final UserRepository userRepo;

    public Cart addToCart(Long userId, Cart item) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Cart> existing = cartRepo.findByUserIdAndProductId(userId, item.getProductId());

        if (existing.isPresent()) {
            Cart cartItem = existing.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            return cartRepo.save(cartItem);
        } else {
            item.setUser(user);
            return cartRepo.save(item);
        }
    }

    public Cart increaseQuantity(Long cartId) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.setQuantity(cart.getQuantity() + 1);
        return cartRepo.save(cart);
    }

    public void deleteCartItem(Long cartId) {
        cartRepo.deleteById(cartId);
    }

    public Cart decreaseQuantity(Long cartId) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getQuantity() > 1) {
            cart.setQuantity(cart.getQuantity() - 1);
        } else {
            cartRepo.delete(cart); // remove if 0
            return null;
        }

        return cartRepo.save(cart);
    }

    public List<Cart> getUserCart(Long userId) {
        return cartRepo.findByUserId(userId);
    }
}
