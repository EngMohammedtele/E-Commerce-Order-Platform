package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.math.*;
import java.util.*;
import static com.codelegends.ecommerce.entity.Enums.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="customer_orders")
// Defines the Order type used by the ecommerce app
public class Order extends BaseClass {
    @Column(nullable=false)
    // Store the orderDate value for this object
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=20)
    // Store the status value for this object
    private OrderStatus status;
    @Column(nullable=false,precision=12,scale=2)
    // Store the totalAmount value for this object
    private BigDecimal totalAmount;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the customer value for this object
    private Customer customer;
    @OneToMany(mappedBy="order",cascade=CascadeType.ALL)
    // Store the list of related items entries
    private List<OrderItem> items=new ArrayList<>();
    @OneToOne(mappedBy="order",cascade=CascadeType.ALL)
    // Store the payment value for this object
    private Payment payment;
    @OneToOne(mappedBy="order",cascade=CascadeType.ALL)
    // Store the shipment value for this object
    private Shipment shipment;
}
