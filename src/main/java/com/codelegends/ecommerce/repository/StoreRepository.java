package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface StoreRepository extends JpaRepository<Store,Long> {
    List<Store> findAllByIsActiveTrue();
    Optional<Store> findByIdAndIsActiveTrue(Long id);
}
