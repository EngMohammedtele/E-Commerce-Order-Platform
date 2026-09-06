package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
// Mark CartItem as a table-backed database entity
@Entity
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
// Give JPA an empty constructor to create objects
@NoArgsConstructor
// Add table rules for unique CartItem records
@Table(uniqueConstraints=@UniqueConstraint(columnNames= {
    "cart_id","product_id"
}
// Defines the CartItem type used by the ecommerce app
// Represents the CartItem database object in Java
)) public class CartItem extends BaseClass {
    // Require the quantity column to have a value
    @Column(nullable=false)
    // Store the quantity value for this object
    // Store how many products are included
    private Integer quantity;
    // Link many CartItem records to one cart record
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the cart value for this object
    private Cart cart;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the product value for this object
    private Product product;
}
