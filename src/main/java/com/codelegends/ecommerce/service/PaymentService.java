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
public class PaymentService implements CrudService<PaymentDTO> {
    private final PaymentRepository repo;
    private final ShipmentRepository shipments;
    private final OrderService orders;
    public PaymentDTO create(PaymentDTO d) {
        Order o=orders.find(d.getOrderId());
        if(repo.findByOrderIdAndIsActiveTrue(o.getId()).isPresent())throw new BusinessException("Order is already paid");
        if(d.getAmount()==null||d.getAmount().compareTo(o.getTotalAmount())!=0)throw new BusinessException("Payment amount must equal order total");
        Payment p=new Payment();
        p.setOrder(o);
        p.setAmount(d.getAmount());
        p.setMethod(d.getMethod());
        p.setStatus(Enums.PaymentStatus.PAID);
        p.setPaidDate(LocalDateTime.now());
        repo.save(p);
        o.setStatus(Enums.OrderStatus.PAID);
        Shipment s=new Shipment();
        s.setOrder(o);
        s.setTrackingNumber("TRK-"+UUID.randomUUID().toString().substring(0,12).toUpperCase());
        s.setStatus(Enums.ShipmentStatus.PREPARING);
        shipments.save(s);
        return PaymentDTO.convertToDTO(p);
    }
    public List<PaymentDTO>getAll() {
        return PaymentDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    public PaymentDTO getById(Long id) {
        return PaymentDTO.convertToDTO(find(id));
    }
    public PaymentDTO update(Long id,PaymentDTO d) {
        throw new BusinessException("Payments cannot be changed");
    }
    public void delete(Long id) {
        Payment e=find(id);
        e.setActive(false);
        repo.save(e);
    }
    Payment find(Long id) {
        return EntityHelper.active(repo,id,"Payment");
    }
}
