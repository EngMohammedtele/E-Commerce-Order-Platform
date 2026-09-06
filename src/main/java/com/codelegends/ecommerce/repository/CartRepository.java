package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
// Describes the CartRepository contract used by this layer
// Access cart records without writing SQL by hand
public interface CartRepository extends JpaRepository<Cart,Long> {
    // Fetch only records that are still active
    List<Cart> findAllByIsActiveTrue();
    // Look up an active record by its id
    Optional<Cart> findByIdAndIsActiveTrue(Long id);
    // Search active records using this field value
    Optional<Cart> findByCustomerIdAndIsActiveTrue(Long id);
}
