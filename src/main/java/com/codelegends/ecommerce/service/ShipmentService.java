package com.codelegends.ecommerce.service;
import com.codelegends.ecommerce.dto.*;
import com.codelegends.ecommerce.entity.*;
import com.codelegends.ecommerce.repository.*;
import com.codelegends.ecommerce.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.time.*;
@Service
@RequiredArgsConstructor
@Transactional
// Contains business rules for shipment data
public class ShipmentService implements CrudService<ShipmentDTO> {
    // Keep the repo dependency ready for this class
    private final ShipmentRepository repo;
    // Create a new record from the supplied data
    public ShipmentDTO create(ShipmentDTO d) {
        // Build a new object before filling its fields
        throw new BusinessException("Shipment is created automatically after payment");
    }
    // Return all active records for this resource
    public List<ShipmentDTO>getAll() {
        // Fetch only records that are still active
        return ShipmentDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    // Find one record using the requested id
    public ShipmentDTO getById(Long id) {
        // Load the active entity or fail when missing
        return ShipmentDTO.convertToDTO(find(id));
    }
    // Update the stored record with new values
    public ShipmentDTO update(Long id,ShipmentDTO d) {
        // Load the active entity or fail when missing
        Shipment s=find(id);
        if(d.getStatus()!=null) {
            // Update this entity field from the DTO data
            s.setStatus(d.getStatus());
            // Update this entity field from the DTO data
            if(d.getStatus()==Enums.ShipmentStatus.SHIPPED)s.setShippedDate(LocalDateTime.now());
        }
        // Return the result to the calling code
        return ShipmentDTO.convertToDTO(repo.save(s));
    }
    // Mark the selected record as inactive
    public void delete(Long id) {
        // Load the active entity or fail when missing
        Shipment e=find(id);
        // Soft delete the record instead of removing it
        e.setActive(false);
        // Save the entity changes in the database
        repo.save(e);
    }
    // Load the active entity or fail when missing
    Shipment find(Long id) {
        // Return the result to the calling code
        return EntityHelper.active(repo,id,"Shipment");
    }
}
