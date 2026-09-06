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
    private boolean isActive=true;
    @Column(nullable=false,updatable=false)
    // Store the createdDate value for this object
    private LocalDateTime createdDate;
    @Column(nullable=false)
    // Store the updatedDate value for this object
    private LocalDateTime updatedDate;
    @PrePersist void prePersist() {
        createdDate=updatedDate=LocalDateTime.now();
        isActive=true;
    }
    @PreUpdate void preUpdate() {
        updatedDate=LocalDateTime.now();
    }
}
