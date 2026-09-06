package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findAllByIsActiveTrue();
    Optional<CartItem> findByIdAndIsActiveTrue(Long id);
    Optional<CartItem> findByCartIdAndProductIdAndIsActiveTrue(Long c,Long p);
}
