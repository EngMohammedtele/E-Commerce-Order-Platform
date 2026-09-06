package com.codelegends.ecommerce.repository;
import com.codelegends.ecommerce.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
// Describes the ShipmentRepository contract used by this layer
// Provides database operations for Shipment entities
public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
    // Fetch only records that are still active
    List<Shipment> findAllByIsActiveTrue();
    // Look up an active record by its id
    // Find one active shipment by its id
    Optional<Shipment> findByIdAndIsActiveTrue(Long id);
}
