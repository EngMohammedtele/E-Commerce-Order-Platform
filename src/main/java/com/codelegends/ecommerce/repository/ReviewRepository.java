package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Review;
import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByIsActiveTrue();
    Optional<Review> findByIdAndIsActiveTrue(Long id);
    boolean existsByCustomerIdAndProductIdAndIsActiveTrue(Long c,Long p);
    @Query("select coalesce(avg(r.rating),0) from Review r where r.product.id=?1 and r.isActive=true") Double averageRating(Long id);
}
