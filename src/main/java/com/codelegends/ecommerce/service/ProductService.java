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
// Contains business rules for product data
public class ProductService implements CrudService<ProductDTO> {
    // Keep the repo dependency ready for this class
    private final ProductRepository repo;
    // Keep the categories dependency ready for this class
    private final CategoryService categories;
    // Create a new record from the supplied data
    public ProductDTO create(ProductDTO d) {
        // Build a new object before filling its fields
        Product e=new Product();
        // Copy DTO values into the entity object
        copy(d,e);
        // Return the result to the calling code
        return ProductDTO.convertToDTO(repo.save(e));
    }
    // Return all active records for this resource
    public List<ProductDTO>getAll() {
        // Fetch only records that are still active
        return ProductDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    // Find one record using the requested id
    public ProductDTO getById(Long id) {
        // Load the active entity or fail when missing
        return ProductDTO.convertToDTO(find(id));
    }
    // Update the stored record with new values
    public ProductDTO update(Long id,ProductDTO d) {
        // Load the active entity or fail when missing
        Product e=find(id);
        // Copy DTO values into the entity object
        copy(d,e);
        // Return the result to the calling code
        return ProductDTO.convertToDTO(repo.save(e));
    }
    // Mark the selected record as inactive
    public void delete(Long id) {
        // Load the active entity or fail when missing
        Product e=find(id);
        // Soft delete the record instead of removing it
        e.setActive(false);
        // Save the entity changes in the database
        repo.save(e);
    }
    // Load the active entity or fail when missing
    Product find(Long id) {
        // Return the result to the calling code
        return EntityHelper.active(repo,id,"Product");
    }
    // Copy DTO values into the entity object
    private void copy(ProductDTO d,Product e) {
        // Load the active entity or fail when missing
        Category c=categories.find(d.getCategoryId());
        // Update this entity field from the DTO data
        e.setName(d.getName());
        // Update this entity field from the DTO data
        e.setPrice(d.getPrice());
        // Update this entity field from the DTO data
        e.setStockQuantity(d.getStockQuantity());
        // Update this entity field from the DTO data
        e.setSku(d.getSku());
        // Update this entity field from the DTO data
        e.setCategory(c);
        // Update this entity field from the DTO data
        e.setStore(c.getStore());
    }
}
