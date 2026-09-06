package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.math.*;
import java.util.*;
import static com.codelegends.ecommerce.entity.Enums.*;
// Mark Order as a table-backed database entity
@Entity
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
// Give JPA an empty constructor to create objects
@NoArgsConstructor
// Map this entity to its database table
@Table(name="customer_orders")
// Defines the Order type used by the ecommerce app
// Represents the Order database object in Java
public class Order extends BaseClass {
    // Require the order date column to have a value
    @Column(nullable=false)
    // Store the orderDate value for this object
    // Save when the order was created
    private LocalDateTime orderDate;
    // Save the status enum as readable text
    @Enumerated(EnumType.STRING)
    // Require the status column to have a value
    @Column(nullable=false,length=20)
    // Store the status value for this object
    // Keep the current Order status
    private OrderStatus status;
    // Require the total amount column to have a value
    @Column(nullable=false,precision=12,scale=2)
    // Store the totalAmount value for this object
    // Store the full money total for this order
    private BigDecimal totalAmount;
    // Link many Order records to one customer record
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the customer value for this object
    // Point this record to its customer
    private Customer customer;
    // Connect this Order to many items records
    @OneToMany(mappedBy="order",cascade=CascadeType.ALL)
    // Store the list of related items entries
    // Keep the child item records for this parent
    private List<OrderItem> items=new ArrayList<>();
    // Connect this Order to one payment record
    @OneToOne(mappedBy="order",cascade=CascadeType.ALL)
    // Store the payment value for this object
    // Connect the order to its payment row
    private Payment payment;
    @OneToOne(mappedBy="order",cascade=CascadeType.ALL)
    // Store the shipment value for this object
    private Shipment shipment;
}
