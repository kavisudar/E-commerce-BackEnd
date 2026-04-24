package com.example.ecom.Repository;

import com.example.ecom.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);


    Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);
}
