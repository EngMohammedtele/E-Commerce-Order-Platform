package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
// Describes the PaymentRepository contract used by this layer
// Works with Payment rows through Spring Data JPA
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    // Fetch only records that are still active
    List<Payment> findAllByIsActiveTrue();
    // Look up an active record by its id
    Optional<Payment> findByIdAndIsActiveTrue(Long id);
    // Search active records using this field value
    Optional<Payment> findByOrderIdAndIsActiveTrue(Long id);
}
