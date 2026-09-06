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
// Contains business rules for review data
public class ReviewService implements CrudService<ReviewDTO> {
    // Keep the repo dependency ready for this class
    private final ReviewRepository repo;
    // Keep the orderItems dependency ready for this class
    private final OrderItemRepository orderItems;
    // Keep the customers dependency ready for this class
    private final CustomerService customers;
    // Keep the products dependency ready for this class
    private final ProductService products;
    // Create a new record from the supplied data
    public ReviewDTO create(ReviewDTO d) {
        // Load the active entity or fail when missing
        Customer c=customers.find(d.getCustomerId());
        // Load the active entity or fail when missing
        Product p=products.find(d.getProductId());
        // Build a new object before filling its fields
        if(!orderItems.existsByOrderCustomerIdAndProductIdAndOrderIsActiveTrue(c.getId(),p.getId()))throw new BusinessException("Customer never ordered this product");
        // Build a new object before filling its fields
        if(repo.existsByCustomerIdAndProductIdAndIsActiveTrue(c.getId(),p.getId()))throw new BusinessException("Customer already reviewed this product");
        // Build a new object before filling its fields
        Review r=new Review();
        // Update this entity field from the DTO data
        r.setCustomer(c);
        // Update this entity field from the DTO data
        r.setProduct(p);
        // Update this entity field from the DTO data
        r.setRating(d.getRating());
        // Update this entity field from the DTO data
        r.setComment(d.getComment());
        // Update this entity field from the DTO data
        r.setReviewDate(LocalDateTime.now());
        // Return the result to the calling code
        return ReviewDTO.convertToDTO(repo.save(r));
    }
    // Return all active records for this resource
    public List<ReviewDTO>getAll() {
        // Fetch only records that are still active
        return ReviewDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    // Find one record using the requested id
    public ReviewDTO getById(Long id) {
        // Load the active entity or fail when missing
        return ReviewDTO.convertToDTO(find(id));
    }
    // Update the stored record with new values
    public ReviewDTO update(Long id,ReviewDTO d) {
        // Load the active entity or fail when missing
        Review r=find(id);
        // Update this entity field from the DTO data
        r.setRating(d.getRating());
        // Update this entity field from the DTO data
        r.setComment(d.getComment());
        // Return the result to the calling code
        return ReviewDTO.convertToDTO(repo.save(r));
    }
    // Mark the selected record as inactive
    public void delete(Long id) {
        // Load the active entity or fail when missing
        Review e=find(id);
        // Soft delete the record instead of removing it
        e.setActive(false);
        // Save the entity changes in the database
        repo.save(e);
    }
    // Load the active entity or fail when missing
    Review find(Long id) {
        // Return the result to the calling code
        return EntityHelper.active(repo,id,"Review");
    }
    public Double average(Long productId) {
        // Load the active entity or fail when missing
        products.find(productId);
        // Return the result to the calling code
        return repo.averageRating(productId);
    }
}
