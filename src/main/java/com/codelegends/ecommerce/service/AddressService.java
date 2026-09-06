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
// Contains business rules for address data
public class AddressService implements CrudService<AddressDTO> {
    // Keep the repo dependency ready for this class
    private final AddressRepository repo;
    // Keep the customers dependency ready for this class
    private final CustomerService customers;
    // Create a new record from the supplied data
    public AddressDTO create(AddressDTO d) {
        // Build a new object before filling its fields
        Address e=new Address();
        // Copy DTO values into the entity object
        copy(d,e);
        // Return the result to the calling code
        return AddressDTO.convertToDTO(repo.save(e));
    }
    // Return all active records for this resource
    public List<AddressDTO>getAll() {
        // Fetch only records that are still active
        return AddressDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    // Find one record using the requested id
    public AddressDTO getById(Long id) {
        // Load the active entity or fail when missing
        return AddressDTO.convertToDTO(find(id));
    }
    // Update the stored record with new values
    public AddressDTO update(Long id,AddressDTO d) {
        // Load the active entity or fail when missing
        Address e=find(id);
        // Copy DTO values into the entity object
        copy(d,e);
        // Return the result to the calling code
        return AddressDTO.convertToDTO(repo.save(e));
    }
    // Mark the selected record as inactive
    public void delete(Long id) {
        // Load the active entity or fail when missing
        Address e=find(id);
        // Soft delete the record instead of removing it
        e.setActive(false);
        // Save the entity changes in the database
        repo.save(e);
    }
    // Load the active entity or fail when missing
    Address find(Long id) {
        // Return the result to the calling code
        return EntityHelper.active(repo,id,"Address");
    }
    // Copy DTO values into the entity object
    private void copy(AddressDTO d,Address e) {
        // Update this entity field from the DTO data
        e.setStreet(d.getStreet());
        // Update this entity field from the DTO data
        e.setCity(d.getCity());
        // Update this entity field from the DTO data
        e.setPostalCode(d.getPostalCode());
        // Update this entity field from the DTO data
        e.setType(d.getType());
        // Load the active entity or fail when missing
        e.setCustomer(customers.find(d.getCustomerId()));
    }
}
