package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
// Mark Review as a table-backed database entity
@Entity
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
// Give JPA an empty constructor to create objects
@NoArgsConstructor
// Add table rules for unique Review records
@Table(uniqueConstraints=@UniqueConstraint(columnNames= {
    "customer_id","product_id"
}
// Defines the Review type used by the ecommerce app
// Represents the Review database object in Java
)) public class Review extends BaseClass {
    // Require the rating column to have a value
    @Column(nullable=false)
    // Store the rating value for this object
    // Save the customer rating number
    private Integer rating;
    // Map the comment field to a database column
    @Column(length=1000)
    // Store the comment value for this object
    // Hold the review text left by the customer
    private String comment;
    // Require the review date column to have a value
    @Column(nullable=false)
    // Store the reviewDate value for this object
    // Store the date and time of the review
    private LocalDateTime reviewDate;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the customer value for this object
    private Customer customer;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the product value for this object
    private Product product;
}
