package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findAllByIsActiveTrue();
    Optional<Customer> findByIdAndIsActiveTrue(Long id);
}
