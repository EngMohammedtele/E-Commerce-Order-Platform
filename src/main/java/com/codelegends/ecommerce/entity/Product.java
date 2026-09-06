package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.*;
// Mark Product as a table-backed database entity
@Entity
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
// Give JPA an empty constructor to create objects
@NoArgsConstructor
// Add table rules for unique Product records
@Table(uniqueConstraints=@UniqueConstraint(columnNames="sku"))
// Defines the Product type used by the ecommerce app
// Represents the Product database object in Java
public class Product extends BaseClass {
    // Require the name column to have a value
    @Column(nullable=false,length=120)
    // Store the name value for this object
    // Store the Product name in the database
    private String name;
    // Require the price column to have a value
    @Column(nullable=false,precision=12,scale=2)
    // Store the price value for this object
    // Store the product selling price
    private BigDecimal price;
    // Require the stock quantity column to have a value
    @Column(nullable=false)
    // Store the stockQuantity value for this object
    // Track how many items are in stock
    private Integer stockQuantity;
    // Require the sku column to have a value
    @Column(nullable=false,length=50)
    // Store the sku value for this object
    // Keep the unique product SKU value
    private String sku;
    // Link many Product records to one category record
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the category value for this object
    // Link this product to its category
    private Category category;
    // Link many Product records to one store record
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the store value for this object
    // Connect this row to the owning store
    private Store store;
    // Connect this Product to many cart items records
    @OneToMany(mappedBy="product")
    // Store the list of related cartItems entries
    // List cart rows that include this product
    private List<CartItem> cartItems=new ArrayList<>();
    // Connect this Product to many order items records
    @OneToMany(mappedBy="product")
    // Store the list of related orderItems entries
    // List order rows that include this product
    private List<OrderItem> orderItems=new ArrayList<>();
    @OneToMany(mappedBy="product")
    // Store the list of related reviews entries
    private List<Review> reviews=new ArrayList<>();
}
