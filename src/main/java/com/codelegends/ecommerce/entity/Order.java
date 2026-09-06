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
public class Order extends BaseClass {
    @Column(nullable=false)
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=20)
    private OrderStatus status;
    @Column(nullable=false,precision=12,scale=2)
    private BigDecimal totalAmount;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    private Customer customer;
    @OneToMany(mappedBy="order",cascade=CascadeType.ALL)
    private List<OrderItem> items=new ArrayList<>();
    @OneToOne(mappedBy="order",cascade=CascadeType.ALL)
    private Payment payment;
    @OneToOne(mappedBy="order",cascade=CascadeType.ALL)
    private Shipment shipment;
}
