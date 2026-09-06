package com.codelegends.ecommerce.service;
import com.codelegends.ecommerce.dto.*;
import com.codelegends.ecommerce.entity.*;
import com.codelegends.ecommerce.repository.*;
import com.codelegends.ecommerce.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.math.*;
import java.time.*;
@Service
@RequiredArgsConstructor
@Transactional
// Contains business rules for order data
public class OrderService implements CrudService<OrderDTO> {
    // Keep the repo dependency ready for this class
    private final OrderRepository repo;
    // Keep the orderItems dependency ready for this class
    private final OrderItemRepository orderItems;
    // Keep the carts dependency ready for this class
    private final CartRepository carts;
    // Keep the cartItems dependency ready for this class
    private final CartItemRepository cartItems;
    // Keep the customers dependency ready for this class
    private final CustomerService customers;
    // Create a new record from the supplied data
    public OrderDTO create(OrderDTO d) {
        // Build a new object before filling its fields
        Order o=new Order();
        // Load the active entity or fail when missing
        o.setCustomer(customers.find(d.getCustomerId()));
        // Update this entity field from the DTO data
        o.setOrderDate(d.getOrderDate()==null?LocalDateTime.now():d.getOrderDate());
        // Update this entity field from the DTO data
        o.setStatus(d.getStatus()==null?Enums.OrderStatus.PENDING:d.getStatus());
        // Update this entity field from the DTO data
        o.setTotalAmount(d.getTotalAmount()==null?BigDecimal.ZERO:d.getTotalAmount());
        // Return the result to the calling code
        return OrderDTO.convertToDTO(repo.save(o));
    }
    // Return all active records for this resource
    public List<OrderDTO>getAll() {
        // Fetch only records that are still active
        return OrderDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    // Find one record using the requested id
    public OrderDTO getById(Long id) {
        // Load the active entity or fail when missing
        return OrderDTO.convertToDTO(find(id));
    }
    // Update the stored record with new values
    public OrderDTO update(Long id,OrderDTO d) {
        // Load the active entity or fail when missing
        Order e=find(id);
        // Update this entity field from the DTO data
        if(d.getStatus()!=null)e.setStatus(d.getStatus());
        // Return the result to the calling code
        return OrderDTO.convertToDTO(repo.save(e));
    }
    // Mark the selected record as inactive
    public void delete(Long id) {
        // Load the active entity or fail when missing
        Order e=find(id);
        // Soft delete the record instead of removing it
        e.setActive(false);
        // Save the entity changes in the database
        repo.save(e);
    }
    // Load the active entity or fail when missing
    Order find(Long id) {
        // Return the result to the calling code
        return EntityHelper.active(repo,id,"Order");
    }
    public List<OrderDTO> customerOrders(Long id) {
        // Load the active entity or fail when missing
        customers.find(id);
        // Search active records using this field value
        return OrderDTO.convertToDTO(repo.findByCustomerIdAndIsActiveTrue(id));
    }
    public BigDecimal totalSpent(Long id) {
        // Load the active entity or fail when missing
        customers.find(id);
        // Return the result to the calling code
        return repo.totalSpent(id);
    }
    public OrderDTO place(Long customerId) {
        // Load the active entity or fail when missing
        Customer customer=customers.find(customerId);
        // Search active records using this field value
        Cart cart=carts.findByCustomerIdAndIsActiveTrue(customerId).orElseThrow(()->new ResourceNotFoundException("Cart not found"));
        List<CartItem> active=cart.getItems().stream().filter(BaseClass::isActive).toList();
        // Build a new object before filling its fields
        if(active.isEmpty())throw new BusinessException("Cart is empty");
        // Build a new object before filling its fields
        for(CartItem i:active)if(i.getQuantity()>i.getProduct().getStockQuantity())throw new BusinessException("Out of stock: "+i.getProduct().getName());
        // Build a new object before filling its fields
        Order order=new Order();
        // Update this entity field from the DTO data
        order.setCustomer(customer);
        // Update this entity field from the DTO data
        order.setOrderDate(LocalDateTime.now());
        // Update this entity field from the DTO data
        order.setStatus(Enums.OrderStatus.PENDING);
        // Update this entity field from the DTO data
        order.setTotalAmount(active.stream().map(i->i.getProduct().getPrice().multiply(BigDecimal.valueOf(i.getQuantity()))).reduce(BigDecimal.ZERO,BigDecimal::add));
        // Save the entity changes in the database
        repo.save(order);
        for(CartItem ci:active) {
            // Build a new object before filling its fields
            OrderItem oi=new OrderItem();
            // Update this entity field from the DTO data
            oi.setOrder(order);
            // Update this entity field from the DTO data
            oi.setProduct(ci.getProduct());
            // Update this entity field from the DTO data
            oi.setQuantity(ci.getQuantity());
            // Update this entity field from the DTO data
            oi.setUnitPrice(ci.getProduct().getPrice());
            // Save the entity changes in the database
            orderItems.save(oi);
            // Update this entity field from the DTO data
            ci.getProduct().setStockQuantity(ci.getProduct().getStockQuantity()-ci.getQuantity());
            // Soft delete the record instead of removing it
            ci.setActive(false);
            // Save the entity changes in the database
            cartItems.save(ci);
            order.getItems().add(oi);
        }
        // Return the result to the calling code
        return OrderDTO.convertToDTO(order);
    }
}
