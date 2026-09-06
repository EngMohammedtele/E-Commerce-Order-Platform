package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.Getter;
// Update this entity field from the DTO data
import lombok.Setter;
import java.time.LocalDateTime;
// Share these fields with child entity tables
@MappedSuperclass
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
// Defines the BaseClass type used by the ecommerce app
// Represents the BaseClass database object in Java
public abstract class BaseClass {
    // Use this field as the primary key
    @Id
    // Generate the id when the row is inserted
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    // Store the id value for this object
    // Store the unique identifier for this entity
    private Long id;
    // Require the is active column to have a value
    @Column(nullable=false)
    // Store the isActive value for this object
    // Keep track of whether this row is active
    private boolean isActive=true;
    // Require the created date column to have a value
    @Column(nullable=false,updatable=false)
    // Store the createdDate value for this object
    // Remember when this row was first created
    private LocalDateTime createdDate;
    // Require the updated date column to have a value
    @Column(nullable=false)
    // Store the updatedDate value for this object
    // Remember when this row was last changed
    private LocalDateTime updatedDate;
    // Run this method before the entity is first saved
    @PrePersist void prePersist() {
        // Set both timestamps when the row is created
        createdDate=updatedDate=LocalDateTime.now();
        // Make new rows active by default
        isActive=true;
    }
    // Run this method before an existing row is updated
    @PreUpdate void preUpdate() {
        // Refresh the update time before saving changes
        updatedDate=LocalDateTime.now();
    }
}
