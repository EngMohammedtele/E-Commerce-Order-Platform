package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Review;
import org.springframework.data.jpa.repository.*;
import java.util.*;
// Describes the ReviewRepository contract used by this layer
public interface ReviewRepository extends JpaRepository<Review,Long> {
// Provides database operations for Review entities
    // Fetch only records that are still active
    List<Review> findAllByIsActiveTrue();
    // Look up an active record by its id
    Optional<Review> findByIdAndIsActiveTrue(Long id);
    boolean existsByCustomerIdAndProductIdAndIsActiveTrue(Long c,Long p);
    // Use this custom SQL query for a special lookup
    @Query("select coalesce(avg(r.rating),0) from Review r where r.product.id=?1 and r.isActive=true") Double averageRating(Long id);
}
    // Check whether this customer already reviewed the product
