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
// Contains business rules for orderitem data
public class OrderItemService implements CrudService<OrderItemDTO> {
    // Keep the repo dependency ready for this class
    private final OrderItemRepository repo;
    // Create a new record from the supplied data
    public OrderItemDTO create(OrderItemDTO d) {
        // Build a new object before filling its fields
        throw new BusinessException("Order items are created by placing a cart order");
    }
    // Return all active records for this resource
    public List<OrderItemDTO>getAll() {
        // Fetch only records that are still active
        return OrderItemDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    // Find one record using the requested id
    public OrderItemDTO getById(Long id) {
        // Load the active entity or fail when missing
        return OrderItemDTO.convertToDTO(find(id));
    }
    // Update the stored record with new values
    public OrderItemDTO update(Long id,OrderItemDTO d) {
        // Build a new object before filling its fields
        throw new BusinessException("Order item snapshots cannot be changed");
    }
    // Mark the selected record as inactive
    public void delete(Long id) {
        // Load the active entity or fail when missing
        OrderItem e=find(id);
        // Soft delete the record instead of removing it
        e.setActive(false);
        // Save the entity changes in the database
        repo.save(e);
    }
    // Load the active entity or fail when missing
    OrderItem find(Long id) {
        // Return the result to the calling code
        return EntityHelper.active(repo,id,"Order item");
    }
}
