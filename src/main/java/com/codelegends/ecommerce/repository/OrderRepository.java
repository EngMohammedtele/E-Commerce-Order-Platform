package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.*;
import java.math.*;
import java.util.*;
// Describes the OrderRepository contract used by this layer
// Provides database operations for Order entities
public interface OrderRepository extends JpaRepository<Order,Long> {
    // Fetch only records that are still active
    List<Order> findAllByIsActiveTrue();
    // Look up an active record by its id
    Optional<Order> findByIdAndIsActiveTrue(Long id);
    // Search active records using this field value
    // Retrieve active orders for the selected customer
    List<Order> findByCustomerIdAndIsActiveTrue(Long id);
    // Use this custom SQL query for a special lookup
    @Query("select coalesce(sum(o.totalAmount),0) from Order o where o.customer.id=?1 and o.isActive=true") BigDecimal totalSpent(Long id);
}
