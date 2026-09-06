package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
    List<Shipment> findAllByIsActiveTrue();
    Optional<Shipment> findByIdAndIsActiveTrue(Long id);
}
