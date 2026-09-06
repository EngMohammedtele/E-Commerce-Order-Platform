package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findAllByIsActiveTrue();
    Optional<OrderItem> findByIdAndIsActiveTrue(Long id);
    boolean existsByOrderCustomerIdAndProductIdAndOrderIsActiveTrue(Long c,Long p);
}
