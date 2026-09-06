package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import static com.codelegends.ecommerce.entity.Enums.*;
// Mark Customer as a table-backed database entity
@Entity
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
// Give JPA an empty constructor to create objects
@NoArgsConstructor
// Add table rules for unique Customer records
@Table(uniqueConstraints=@UniqueConstraint(columnNames="email"))
// Defines the Customer type used by the ecommerce app
// Represents the Customer database object in Java
public class Customer extends BaseClass {
    // Require the name column to have a value
    @Column(nullable=false,length=100)
    // Store the name value for this object
    // Store the Customer name in the database
    private String name;
    // Require the email column to have a value
    @Column(nullable=false,length=150)
    // Store the email value for this object
    // Hold the email used to identify the customer
    private String email;
    // Require the phone number column to have a value
    @Column(nullable=false,length=20)
    // Store the phoneNumber value for this object
    // Save the customer phone number
    private String phoneNumber;
    // Save the gender enum as readable text
    @Enumerated(EnumType.STRING)
    // Require the gender column to have a value
    @Column(nullable=false,length=10)
    // Store the gender value for this object
    // Store the selected gender enum value
    private Gender gender;
    // Link many Customer records to one store record
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the store value for this object
    // Connect this row to the owning store
    private Store store;
    // Connect this Customer to one cart record
    @OneToOne(mappedBy="customer")
    // Store the cart value for this object
    // Point this item back to its cart
    private Cart cart;
    // Connect this Customer to many addresses records
    @OneToMany(mappedBy="customer")
    // Store the list of related addresses entries
    // Store all address rows for this customer
    private List<Address> addresses=new ArrayList<>();
    // Connect this Customer to many orders records
    @OneToMany(mappedBy="customer")
    // Store the list of related orders entries
    // Keep every order connected to this customer
    private List<Order> orders=new ArrayList<>();
    // Connect this Customer to many reviews records
    @OneToMany(mappedBy="customer")
    // Store the list of related reviews entries
    // Store review rows connected to this record
    private List<Review> reviews=new ArrayList<>();
}
