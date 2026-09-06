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
// Contains business rules for payment data
public class PaymentService implements CrudService<PaymentDTO> {
    // Keep the repo dependency ready for this class
    private final PaymentRepository repo;
    // Keep the shipments dependency ready for this class
    private final ShipmentRepository shipments;
    // Keep the orders dependency ready for this class
    private final OrderService orders;
    // Create a new record from the supplied data
    public PaymentDTO create(PaymentDTO d) {
        // Load the active entity or fail when missing
        Order o=orders.find(d.getOrderId());
        // Search active records using this field value
        if(repo.findByOrderIdAndIsActiveTrue(o.getId()).isPresent())throw new BusinessException("Order is already paid");
        // Build a new object before filling its fields
        if(d.getAmount()==null||d.getAmount().compareTo(o.getTotalAmount())!=0)throw new BusinessException("Payment amount must equal order total");
        // Build a new object before filling its fields
        Payment p=new Payment();
        // Update this entity field from the DTO data
        p.setOrder(o);
        // Update this entity field from the DTO data
        p.setAmount(d.getAmount());
        // Update this entity field from the DTO data
        p.setMethod(d.getMethod());
        // Update this entity field from the DTO data
        p.setStatus(Enums.PaymentStatus.PAID);
        // Update this entity field from the DTO data
        p.setPaidDate(LocalDateTime.now());
        // Save the entity changes in the database
        repo.save(p);
        // Update this entity field from the DTO data
        o.setStatus(Enums.OrderStatus.PAID);
        // Build a new object before filling its fields
        Shipment s=new Shipment();
        // Update this entity field from the DTO data
        s.setOrder(o);
        // Update this entity field from the DTO data
        s.setTrackingNumber("TRK-"+UUID.randomUUID().toString().substring(0,12).toUpperCase());
        // Update this entity field from the DTO data
        s.setStatus(Enums.ShipmentStatus.PREPARING);
        // Save the entity changes in the database
        shipments.save(s);
        // Return the result to the calling code
        return PaymentDTO.convertToDTO(p);
    }
    // Return all active records for this resource
    public List<PaymentDTO>getAll() {
        // Fetch only records that are still active
        return PaymentDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    // Find one record using the requested id
    public PaymentDTO getById(Long id) {
        // Load the active entity or fail when missing
        return PaymentDTO.convertToDTO(find(id));
    }
    // Update the stored record with new values
    public PaymentDTO update(Long id,PaymentDTO d) {
        // Build a new object before filling its fields
        throw new BusinessException("Payments cannot be changed");
    }
    // Mark the selected record as inactive
    public void delete(Long id) {
        // Load the active entity or fail when missing
        Payment e=find(id);
        // Soft delete the record instead of removing it
        e.setActive(false);
        // Save the entity changes in the database
        repo.save(e);
    }
    // Load the active entity or fail when missing
    Payment find(Long id) {
        // Return the result to the calling code
        return EntityHelper.active(repo,id,"Payment");
    }
}
