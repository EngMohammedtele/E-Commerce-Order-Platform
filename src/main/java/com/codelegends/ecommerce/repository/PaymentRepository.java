package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findAllByIsActiveTrue();
    Optional<Payment> findByIdAndIsActiveTrue(Long id);
    Optional<Payment> findByOrderIdAndIsActiveTrue(Long id);
}
