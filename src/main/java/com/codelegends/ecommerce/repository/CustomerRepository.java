package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
// Describes the CustomerRepository contract used by this layer
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    // Fetch only records that are still active
    List<Customer> findAllByIsActiveTrue();
    // Look up an active record by its id
    Optional<Customer> findByIdAndIsActiveTrue(Long id);
}
