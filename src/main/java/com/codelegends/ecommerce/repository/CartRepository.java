package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByIsActiveTrue();
    Optional<Cart> findByIdAndIsActiveTrue(Long id);
    Optional<Cart> findByCustomerIdAndIsActiveTrue(Long id);
}
