package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
// Describes the AddressRepository contract used by this layer
public interface AddressRepository extends JpaRepository<Address,Long> {
    // Fetch only records that are still active
    List<Address> findAllByIsActiveTrue();
    // Look up an active record by its id
    Optional<Address> findByIdAndIsActiveTrue(Long id);
}
