package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.*;
import java.math.*;
import java.util.*;
// Describes the ProductRepository contract used by this layer
// Provides database operations for Product entities
public interface ProductRepository extends JpaRepository<Product,Long> {
    // Fetch only records that are still active
    List<Product> findAllByIsActiveTrue();
    // Look up an active record by its id
    Optional<Product> findByIdAndIsActiveTrue(Long id);
    // Search active records using this field value
    List<Product> findByCategoryIdAndIsActiveTrue(Long id);
    // Search active records using this field value
    List<Product> findByPriceLessThanAndIsActiveTrue(BigDecimal price);
    // Search active records using this field value
    List<Product> findByStockQuantityLessThanAndIsActiveTrue(Integer threshold);
    // Use this custom SQL query for a special lookup
    @Query(value="select p.* from product p join order_item oi on oi.product_id=p.id where p.is_active=true and oi.is_active=true group by p.id order by sum(oi.quantity) desc limit 1",nativeQuery=true) Optional<Product> bestSelling();
}
