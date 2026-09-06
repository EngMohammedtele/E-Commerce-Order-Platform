package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface AddressRepository extends JpaRepository<Address,Long> {
    List<Address> findAllByIsActiveTrue();
    Optional<Address> findByIdAndIsActiveTrue(Long id);
}
