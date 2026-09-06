package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
// Mark Cart as a table-backed database entity
@Entity
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
// Give JPA an empty constructor to create objects
@NoArgsConstructor
// Defines the Cart type used by the ecommerce app
// Represents the Cart database object in Java
public class Cart extends BaseClass {
    // Connect this Cart to one customer record
    @OneToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(unique=true)
    // Store the customer value for this object
    private Customer customer;
    @OneToMany(mappedBy="cart",cascade=CascadeType.ALL,orphanRemoval=true)
    // Store the list of related items entries
    private List<CartItem> items=new ArrayList<>();
}
