package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.*;
// Mark OrderItem as a table-backed database entity
@Entity
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
// Give JPA an empty constructor to create objects
@NoArgsConstructor
// Defines the OrderItem type used by the ecommerce app
// Represents the OrderItem database object in Java
public class OrderItem extends BaseClass {
    // Require the quantity column to have a value
    @Column(nullable=false)
    // Store the quantity value for this object
    private Integer quantity;
    @Column(nullable=false,precision=12,scale=2)
    // Store the unitPrice value for this object
    private BigDecimal unitPrice;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the order value for this object
    private Order order;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the product value for this object
    private Product product;
}
