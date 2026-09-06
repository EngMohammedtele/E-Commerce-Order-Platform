package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
// Describes the StoreRepository contract used by this layer
// Provides database operations for Store entities
public interface StoreRepository extends JpaRepository<Store,Long> {
    // Fetch only records that are still active
    List<Store> findAllByIsActiveTrue();
    // Look up an active record by its id
    Optional<Store> findByIdAndIsActiveTrue(Long id);
}
