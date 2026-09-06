package com.codelegends.ecommerce.service;
import com.codelegends.ecommerce.dto.*;
import com.codelegends.ecommerce.entity.*;
import com.codelegends.ecommerce.repository.*;
import com.codelegends.ecommerce.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
@RequiredArgsConstructor
@Transactional
// Contains business rules for cartitem data
public class CartItemService implements CrudService<CartItemDTO> {
    // Keep the repo dependency ready for this class
    private final CartItemRepository repo;
    // Create a new record from the supplied data
    public CartItemDTO create(CartItemDTO d) {
        // Build a new object before filling its fields
        throw new BusinessException("Use the customer cart endpoints");
    }
    // Return all active records for this resource
    public List<CartItemDTO>getAll() {
        // Fetch only records that are still active
        return CartItemDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    // Find one record using the requested id
    public CartItemDTO getById(Long id) {
        // Load the active entity or fail when missing
        return CartItemDTO.convertToDTO(find(id));
    }
    // Update the stored record with new values
    public CartItemDTO update(Long id,CartItemDTO d) {
        // Build a new object before filling its fields
        throw new BusinessException("Use the customer cart endpoints");
    }
    // Mark the selected record as inactive
    public void delete(Long id) {
        // Load the active entity or fail when missing
        CartItem e=find(id);
        // Soft delete the record instead of removing it
        e.setActive(false);
        // Save the entity changes in the database
        repo.save(e);
    }
    // Load the active entity or fail when missing
    CartItem find(Long id) {
        // Return the result to the calling code
        return EntityHelper.active(repo,id,"Cart item");
    }
}
