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
    // Retrieve active products in the chosen category
    List<Product> findByCategoryIdAndIsActiveTrue(Long id);
    // Search active records using this field value
    // Find active products below a requested price
    List<Product> findByPriceLessThanAndIsActiveTrue(BigDecimal price);
    // Search active records using this field value
    // Find active products with low stock
    List<Product> findByStockQuantityLessThanAndIsActiveTrue(Integer threshold);
    // Use this custom SQL query for a special lookup
    // Run native SQL to find the best selling product
    // Return the top product only when sales data exists
    @Query(value="select p.* from product p join order_item oi on oi.product_id=p.id where p.is_active=true and oi.is_active=true group by p.id order by sum(oi.quantity) desc limit 1",nativeQuery=true) Optional<Product> bestSelling();
}
