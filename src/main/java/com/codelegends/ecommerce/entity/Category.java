package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
// Mark Category as a table-backed database entity
@Entity
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
// Give JPA an empty constructor to create objects
@NoArgsConstructor
// Defines the Category type used by the ecommerce app
// Represents the Category database object in Java
public class Category extends BaseClass {
    // Require the name column to have a value
    @Column(nullable=false,length=100)
    // Store the name value for this object
    // Store the Category name in the database
    private String name;
    // Map the description field to a database column
    @Column(length=500)
    // Store the description value for this object
    // Hold the optional category description text
    private String description;
    // Link many Category records to one store record
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the store value for this object
    // Connect this row to the owning store
    private Store store;
    // Connect this Category to many products records
    @OneToMany(mappedBy="category")
    // Store the list of related products entries
    private List<Product> products=new ArrayList<>();
}
