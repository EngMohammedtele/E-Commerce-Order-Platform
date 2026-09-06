package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.*;
import java.math.*;
import java.util.*;
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByIsActiveTrue();
    Optional<Product> findByIdAndIsActiveTrue(Long id);
    List<Product> findByCategoryIdAndIsActiveTrue(Long id);
    List<Product> findByPriceLessThanAndIsActiveTrue(BigDecimal price);
    List<Product> findByStockQuantityLessThanAndIsActiveTrue(Integer threshold);
    @Query(value="select p.* from product p join order_item oi on oi.product_id=p.id where p.is_active=true and oi.is_active=true group by p.id order by sum(oi.quantity) desc limit 1",nativeQuery=true) Optional<Product> bestSelling();
}
