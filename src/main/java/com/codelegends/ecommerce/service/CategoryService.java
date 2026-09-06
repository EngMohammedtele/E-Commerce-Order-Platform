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
// Contains business rules for category data
public class CategoryService implements CrudService<CategoryDTO> {
    // Keep the repo dependency ready for this class
    private final CategoryRepository repo;
    // Keep the stores dependency ready for this class
    private final StoreService stores;
    // Create a new record from the supplied data
    public CategoryDTO create(CategoryDTO d) {
        // Build a new object before filling its fields
        Category e=new Category();
        // Copy DTO values into the entity object
        copy(d,e);
        // Return the result to the calling code
        return CategoryDTO.convertToDTO(repo.save(e));
    }
    // Return all active records for this resource
    public List<CategoryDTO>getAll() {
        // Fetch only records that are still active
        return CategoryDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    // Find one record using the requested id
    public CategoryDTO getById(Long id) {
        // Load the active entity or fail when missing
        return CategoryDTO.convertToDTO(find(id));
    }
    // Update the stored record with new values
    public CategoryDTO update(Long id,CategoryDTO d) {
        // Load the active entity or fail when missing
        Category e=find(id);
        // Copy DTO values into the entity object
        copy(d,e);
        // Return the result to the calling code
        return CategoryDTO.convertToDTO(repo.save(e));
    }
    // Mark the selected record as inactive
    public void delete(Long id) {
        // Load the active entity or fail when missing
        Category e=find(id);
        // Soft delete the record instead of removing it
        e.setActive(false);
        // Save the entity changes in the database
        repo.save(e);
    }
    // Load the active entity or fail when missing
    Category find(Long id) {
        // Return the result to the calling code
        return EntityHelper.active(repo,id,"Category");
    }
    // Copy DTO values into the entity object
    private void copy(CategoryDTO d,Category e) {
        // Update this entity field from the DTO data
        e.setName(d.getName());
        // Update this entity field from the DTO data
        e.setDescription(d.getDescription());
        // Load the active entity or fail when missing
        e.setStore(stores.find(d.getStoreId()));
    }
}
