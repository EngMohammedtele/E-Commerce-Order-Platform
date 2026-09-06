package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
// Describes the CustomerRepository contract used by this layer
// Provides database operations for Customer entities
// Inherit standard save, find, and delete methods from JpaRepository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    // Fetch only records that are still active
    List<Customer> findAllByIsActiveTrue();
    // Look up an active record by its id
    // Look up a customer by id while ignoring inactive rows
    Optional<Customer> findByIdAndIsActiveTrue(Long id);
}
