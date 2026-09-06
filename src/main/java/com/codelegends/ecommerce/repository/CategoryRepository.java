package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByIsActiveTrue();
    Optional<Category> findByIdAndIsActiveTrue(Long id);
}
