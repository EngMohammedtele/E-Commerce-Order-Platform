package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
// Describes the OrderItemRepository contract used by this layer
// Handles database access for order item records
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    // Fetch only records that are still active
    // Return active order items in a list
    List<OrderItem> findAllByIsActiveTrue();
    // Look up an active record by its id
    Optional<OrderItem> findByIdAndIsActiveTrue(Long id);
    boolean existsByOrderCustomerIdAndProductIdAndOrderIsActiveTrue(Long c,Long p);
}
