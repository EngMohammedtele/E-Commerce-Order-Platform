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
public class OrderService implements CrudService<OrderDTO> {
    private final OrderRepository repo;
    private final OrderItemRepository orderItems;
    private final CartRepository carts;
    private final CartItemRepository cartItems;
    private final CustomerService customers;
    public OrderDTO create(OrderDTO d) {
        Order o=new Order();
        o.setCustomer(customers.find(d.getCustomerId()));
        o.setOrderDate(d.getOrderDate()==null?LocalDateTime.now():d.getOrderDate());
        o.setStatus(d.getStatus()==null?Enums.OrderStatus.PENDING:d.getStatus());
        o.setTotalAmount(d.getTotalAmount()==null?BigDecimal.ZERO:d.getTotalAmount());
        return OrderDTO.convertToDTO(repo.save(o));
    }
    public List<OrderDTO>getAll() {
        return OrderDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    public OrderDTO getById(Long id) {
        return OrderDTO.convertToDTO(find(id));
    }
    public OrderDTO update(Long id,OrderDTO d) {
        Order e=find(id);
        if(d.getStatus()!=null)e.setStatus(d.getStatus());
        return OrderDTO.convertToDTO(repo.save(e));
    }
    public void delete(Long id) {
        Order e=find(id);
        e.setActive(false);
        repo.save(e);
    }
    Order find(Long id) {
        return EntityHelper.active(repo,id,"Order");
    }
    public List<OrderDTO> customerOrders(Long id) {
        customers.find(id);
        return OrderDTO.convertToDTO(repo.findByCustomerIdAndIsActiveTrue(id));
    }
    public BigDecimal totalSpent(Long id) {
        customers.find(id);
        return repo.totalSpent(id);
    }
    public OrderDTO place(Long customerId) {
        Customer customer=customers.find(customerId);
        Cart cart=carts.findByCustomerIdAndIsActiveTrue(customerId).orElseThrow(()->new ResourceNotFoundException("Cart not found"));
        List<CartItem> active=cart.getItems().stream().filter(BaseClass::isActive).toList();
        if(active.isEmpty())throw new BusinessException("Cart is empty");
        for(CartItem i:active)if(i.getQuantity()>i.getProduct().getStockQuantity())throw new BusinessException("Out of stock: "+i.getProduct().getName());
        Order order=new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Enums.OrderStatus.PENDING);
        order.setTotalAmount(active.stream().map(i->i.getProduct().getPrice().multiply(BigDecimal.valueOf(i.getQuantity()))).reduce(BigDecimal.ZERO,BigDecimal::add));
        repo.save(order);
        for(CartItem ci:active) {
            OrderItem oi=new OrderItem();
            oi.setOrder(order);
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setUnitPrice(ci.getProduct().getPrice());
            orderItems.save(oi);
            ci.getProduct().setStockQuantity(ci.getProduct().getStockQuantity()-ci.getQuantity());
            ci.setActive(false);
            cartItems.save(ci);
            order.getItems().add(oi);
        }
        return OrderDTO.convertToDTO(order);
    }
}
