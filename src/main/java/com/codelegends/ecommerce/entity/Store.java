package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
// Mark Store as a table-backed database entity
@Entity
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
// Give JPA an empty constructor to create objects
@NoArgsConstructor
// Defines the Store type used by the ecommerce app
// Represents the Store database object in Java
public class Store extends BaseClass {
    // Require the name column to have a value
    @Column(nullable=false,length=100)
    // Store the name value for this object
    // Store the Store name in the database
    private String name;
    // Require the location column to have a value
    @Column(nullable=false,length=200)
    // Store the location value for this object
    // Store the physical location of the store
    private String location;
    // Connect this Store to many categories records
    @OneToMany(mappedBy="store")
    // Store the list of related categories entries
    // Keep categories that belong to this store
    private List<Category> categories=new ArrayList<>();
    // Connect this Store to many products records
    @OneToMany(mappedBy="store")
    // Store the list of related products entries
    private List<Product> products=new ArrayList<>();
    @OneToMany(mappedBy="store")
    // Store the list of related customers entries
    private List<Customer> customers=new ArrayList<>();
}
