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
// Contains business rules for customer data
public class CustomerService implements CrudService<CustomerDTO> {
    // Keep the repo dependency ready for this class
    private final CustomerRepository repo;
    // Keep the stores dependency ready for this class
    private final StoreService stores;
    // Keep the carts dependency ready for this class
    private final CartRepository carts;
    // Create a new record from the supplied data
    public CustomerDTO create(CustomerDTO d) {
        // Build a new object before filling its fields
        Customer e=new Customer();
        // Copy DTO values into the entity object
        copy(d,e);
        // Save the entity changes in the database
        repo.save(e);
        // Build a new object before filling its fields
        Cart c=new Cart();
        // Update this entity field from the DTO data
        c.setCustomer(e);
        // Save the entity changes in the database
        carts.save(c);
        // Return the result to the calling code
        return CustomerDTO.convertToDTO(e);
    }
    // Return all active records for this resource
    public List<CustomerDTO>getAll() {
        // Fetch only records that are still active
        return CustomerDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    // Find one record using the requested id
    public CustomerDTO getById(Long id) {
        // Load the active entity or fail when missing
        return CustomerDTO.convertToDTO(find(id));
    }
    // Update the stored record with new values
    public CustomerDTO update(Long id,CustomerDTO d) {
        // Load the active entity or fail when missing
        Customer e=find(id);
        // Copy DTO values into the entity object
        copy(d,e);
        // Return the result to the calling code
        return CustomerDTO.convertToDTO(repo.save(e));
    }
    // Mark the selected record as inactive
    public void delete(Long id) {
        // Load the active entity or fail when missing
        Customer e=find(id);
        // Soft delete the record instead of removing it
        e.setActive(false);
        // Save the entity changes in the database
        repo.save(e);
    }
    // Load the active entity or fail when missing
    Customer find(Long id) {
        // Return the result to the calling code
        return EntityHelper.active(repo,id,"Customer");
    }
    // Copy DTO values into the entity object
    private void copy(CustomerDTO d,Customer e) {
        // Update this entity field from the DTO data
        e.setName(d.getName());
        // Update this entity field from the DTO data
        e.setEmail(d.getEmail());
        // Update this entity field from the DTO data
        e.setPhoneNumber(d.getPhoneNumber());
        // Update this entity field from the DTO data
        e.setGender(d.getGender());
        // Load the active entity or fail when missing
        e.setStore(stores.find(d.getStoreId()));
    }
}
