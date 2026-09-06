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
// Contains business rules for cart data
public class CartService implements CrudService<CartDTO> {
    // Keep the repo dependency ready for this class
    private final CartRepository repo;
    // Keep the items dependency ready for this class
    private final CartItemRepository items;
    // Keep the customers dependency ready for this class
    private final CustomerService customers;
    // Keep the products dependency ready for this class
    private final ProductService products;
    // Create a new record from the supplied data
    public CartDTO create(CartDTO d) {
        // Build a new object before filling its fields
        Cart e=new Cart();
        // Load the active entity or fail when missing
        e.setCustomer(customers.find(d.getCustomerId()));
        // Return the result to the calling code
        return CartDTO.convertToDTO(repo.save(e));
    }
    // Return all active records for this resource
    public List<CartDTO>getAll() {
        // Fetch only records that are still active
        return CartDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    // Find one record using the requested id
    public CartDTO getById(Long id) {
        // Load the active entity or fail when missing
        return CartDTO.convertToDTO(find(id));
    }
    // Update the stored record with new values
    public CartDTO update(Long id,CartDTO d) {
        // Load the active entity or fail when missing
        Cart e=find(id);
        // Load the active entity or fail when missing
        e.setCustomer(customers.find(d.getCustomerId()));
        // Return the result to the calling code
        return CartDTO.convertToDTO(repo.save(e));
    }
    // Mark the selected record as inactive
    public void delete(Long id) {
        // Load the active entity or fail when missing
        Cart e=find(id);
        // Soft delete the record instead of removing it
        e.setActive(false);
        // Save the entity changes in the database
        repo.save(e);
    }
    // Load the active entity or fail when missing
    Cart find(Long id) {
        // Return the result to the calling code
        return EntityHelper.active(repo,id,"Cart");
    }
    public CartDTO add(Long customerId,Long productId,int quantity) {
        // Build a new object before filling its fields
        if(quantity<1)throw new BusinessException("Quantity must be positive");
        // Search active records using this field value
        Cart c=repo.findByCustomerIdAndIsActiveTrue(customerId).orElseThrow(()->new ResourceNotFoundException("Cart not found"));
        // Load the active entity or fail when missing
        Product p=products.find(productId);
        // Search active records using this field value
        CartItem item=items.findByCartIdAndProductIdAndIsActiveTrue(c.getId(),p.getId()).orElse(null);
        int total=quantity+(item==null?0:item.getQuantity());
        // Build a new object before filling its fields
        if(total>p.getStockQuantity())throw new BusinessException("Requested quantity exceeds stock");
        if(item==null) {
            // Build a new object before filling its fields
            item=new CartItem();
            // Update this entity field from the DTO data
            item.setCart(c);
            // Update this entity field from the DTO data
            item.setProduct(p);
        }
        // Update this entity field from the DTO data
        item.setQuantity(total);
        // Save the entity changes in the database
        items.save(item);
        // Return the result to the calling code
        return CartDTO.convertToDTO(repo.findById(c.getId()).orElseThrow());
    }
    public CartDTO quantity(Long customerId,Long itemId,int q) {
        // Build a new object before filling its fields
        if(q<1)throw new BusinessException("Quantity must be positive");
        CartItem i=EntityHelper.active(items,itemId,"Cart item");
        // Build a new object before filling its fields
        if(!i.getCart().getCustomer().getId().equals(customerId))throw new BusinessException("Item does not belong to customer cart");
        // Build a new object before filling its fields
        if(q>i.getProduct().getStockQuantity())throw new BusinessException("Requested quantity exceeds stock");
        // Update this entity field from the DTO data
        i.setQuantity(q);
        // Save the entity changes in the database
        items.save(i);
        // Return the result to the calling code
        return CartDTO.convertToDTO(i.getCart());
    }
    public void remove(Long customerId,Long itemId) {
        CartItem i=EntityHelper.active(items,itemId,"Cart item");
        // Build a new object before filling its fields
        if(!i.getCart().getCustomer().getId().equals(customerId))throw new BusinessException("Item does not belong to customer cart");
        // Soft delete the record instead of removing it
        i.setActive(false);
        // Save the entity changes in the database
        items.save(i);
    }
}
