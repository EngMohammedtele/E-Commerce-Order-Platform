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
    @Column(nullable=false,length=100)
    // Store the name value for this object
    private String name;
    @Column(nullable=false,length=150)
    // Store the email value for this object
    private String email;
    @Column(nullable=false,length=20)
    // Store the phoneNumber value for this object
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=10)
    // Store the gender value for this object
    private Gender gender;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the store value for this object
    private Store store;
    @OneToOne(mappedBy="customer")
    // Store the cart value for this object
    private Cart cart;
    @OneToMany(mappedBy="customer")
    // Store the list of related addresses entries
    private List<Address> addresses=new ArrayList<>();
    @OneToMany(mappedBy="customer")
    // Store the list of related orders entries
    private List<Order> orders=new ArrayList<>();
    @OneToMany(mappedBy="customer")
    // Store the list of related reviews entries
    private List<Review> reviews=new ArrayList<>();
}
