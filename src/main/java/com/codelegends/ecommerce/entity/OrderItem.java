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
    // Store how many products are included
    private Integer quantity;
    // Require the unit price column to have a value
    @Column(nullable=false,precision=12,scale=2)
    // Store the unitPrice value for this object
    // Store the product price used on this order line
    private BigDecimal unitPrice;
    // Link many OrderItem records to one order record
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the order value for this object
    private Order order;
    // Link many OrderItem records to one product record
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the product value for this object
    // Link this row to the related product
    private Product product;
}
