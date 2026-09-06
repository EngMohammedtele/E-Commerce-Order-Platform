package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
// Describes the CategoryRepository contract used by this layer
// Stores query methods for Category entities
public interface CategoryRepository extends JpaRepository<Category,Long> {
    // Fetch only records that are still active
    List<Category> findAllByIsActiveTrue();
    // Look up an active record by its id
    Optional<Category> findByIdAndIsActiveTrue(Long id);
}
