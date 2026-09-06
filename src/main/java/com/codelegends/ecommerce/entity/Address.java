package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import static com.codelegends.ecommerce.entity.Enums.*;
// Mark Address as a table-backed database entity
@Entity
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
// Give JPA an empty constructor to create objects
@NoArgsConstructor
// Defines the Address type used by the ecommerce app
// Represents the Address database object in Java
public class Address extends BaseClass {
    // Require the street column to have a value
    @Column(nullable=false,length=200)
    // Store the street value for this object
    // Hold the street part of the saved address
    private String street;
    // Require the city column to have a value
    @Column(nullable=false,length=100)
    // Store the city value for this object
    // Store the city for this address row
    private String city;
    // Require the postal code column to have a value
    @Column(nullable=false,length=20)
    // Store the postalCode value for this object
    // Keep the postal code for delivery details
    private String postalCode;
    // Save the type enum as readable text
    @Enumerated(EnumType.STRING)
    // Require the type column to have a value
    @Column(nullable=false,length=10)
    // Store the type value for this object
    // Save which kind of address this is
    private AddressType type;
    // Link many Address records to one customer record
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the customer value for this object
    // Point this record to its customer
    private Customer customer;
}
