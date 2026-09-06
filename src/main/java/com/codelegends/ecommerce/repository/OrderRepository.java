package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.*;
import java.math.*;
import java.util.*;
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByIsActiveTrue();
    Optional<Order> findByIdAndIsActiveTrue(Long id);
    List<Order> findByCustomerIdAndIsActiveTrue(Long id);
    @Query("select coalesce(sum(o.totalAmount),0) from Order o where o.customer.id=?1 and o.isActive=true") BigDecimal totalSpent(Long id);
}
