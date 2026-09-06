package com.codelegends.ecommerce.service;
import com.codelegends.ecommerce.dto.*;
import com.codelegends.ecommerce.entity.*;
import com.codelegends.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
@RequiredArgsConstructor
@Transactional
// Contains business rules for store data
public class StoreService implements CrudService<StoreDTO> {
    // Keep the repo dependency ready for this class
    private final StoreRepository repo;
    // Create a new record from the supplied data
    public StoreDTO create(StoreDTO d) {
        // Build summary numbers for one store
        Store e=new Store();
        // Copy DTO values into the entity object
        copy(d,e);
        // Return the result to the calling code
        return StoreDTO.convertToDTO(repo.save(e));
    }
    // Return all active records for this resource
    public List<StoreDTO>getAll() {
        // Fetch only records that are still active
        return StoreDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    // Find one record using the requested id
    public StoreDTO getById(Long id) {
        // Load the active entity or fail when missing
        return StoreDTO.convertToDTO(find(id));
    }
    // Update the stored record with new values
    public StoreDTO update(Long id,StoreDTO d) {
        // Load the active entity or fail when missing
        Store e=find(id);
        // Copy DTO values into the entity object
        copy(d,e);
        // Return the result to the calling code
        return StoreDTO.convertToDTO(repo.save(e));
    }
    // Mark the selected record as inactive
    public void delete(Long id) {
        // Load the active entity or fail when missing
        Store e=find(id);
        // Soft delete the record instead of removing it
        e.setActive(false);
        // Save the entity changes in the database
        repo.save(e);
    }
    // Load the active entity or fail when missing
    Store find(Long id) {
        // Return the result to the calling code
        return EntityHelper.active(repo,id,"Store");
    }
    // Copy DTO values into the entity object
    private void copy(StoreDTO d,Store e) {
        // Update this entity field from the DTO data
        e.setName(d.getName());
        // Update this entity field from the DTO data
        e.setLocation(d.getLocation());
    }
}
