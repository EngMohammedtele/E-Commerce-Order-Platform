package com.codelegends.ecommerce.service;
import java.util.List;
// Describes the CrudService contract used by this layer
public interface CrudService<D> {
    // Create a new record from the supplied data
    D create(D dto);
    // Return all active records for this resource
    List<D> getAll();
    // Find one record using the requested id
    D getById(Long id);
    // Update the stored record with new values
    D update(Long id,D dto);
    // Mark the selected record as inactive
    void delete(Long id);
}
