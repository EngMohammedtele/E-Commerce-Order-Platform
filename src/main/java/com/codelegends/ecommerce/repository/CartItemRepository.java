package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
// Describes the CartItemRepository contract used by this layer
// Provides database access for cart item rows
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    // Fetch only records that are still active
    List<CartItem> findAllByIsActiveTrue();
    // Look up an active record by its id
    Optional<CartItem> findByIdAndIsActiveTrue(Long id);
    // Search active records using this field value
    Optional<CartItem> findByCartIdAndProductIdAndIsActiveTrue(Long c,Long p);
}
