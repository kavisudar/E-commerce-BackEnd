package com.example.ecom.Service;

import com.example.ecom.Model.Cart;
import com.example.ecom.Repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartRepo;

    private static final int DEFAULT_STEP = 1;
    private static final int SPECIAL_STEP = 2;
    private static final List<Long> SPECIAL_PRODUCT_IDS = List.of(1L, 4L);


    public Cart addToCart(Long userId, Cart item) {
        item.setUserId(userId);
        return cartRepo.save(item);
    }

    public List<Cart> getUserCart(Long userId) {
        return cartRepo.findByUserId(userId);
    }

    public void deleteCartItem(Long cartId) {
        cartRepo.deleteById(cartId);
    }

    public ResponseEntity<?> increaseQty(Long id) {
        return cartRepo.findById(id).map(item -> {
            int step = SPECIAL_PRODUCT_IDS.contains(item.getProductId())
                    ? SPECIAL_STEP
                    : DEFAULT_STEP;
            item.setQuantity(item.getQuantity() + step);
            return ResponseEntity.ok(cartRepo.save(item));
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> decreaseQty(Long id) {
        return cartRepo.findById(id).map(item -> {
            int step = SPECIAL_PRODUCT_IDS.contains(item.getProductId())
                    ? SPECIAL_STEP
                    : DEFAULT_STEP;
            int newQty = item.getQuantity() - step;
            if (newQty <= 0) {
                cartRepo.deleteById(id);
                return ResponseEntity.ok(Map.of("message", "removed"));
            }
            item.setQuantity(newQty);
            return ResponseEntity.ok(cartRepo.save(item));
        }).orElse(ResponseEntity.notFound().build());
    }
}