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
@NoArgsConstructor
// Defines the Cart type used by the ecommerce app
public class Cart extends BaseClass {
    @OneToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(unique=true)
    // Store the customer value for this object
    private Customer customer;
    @OneToMany(mappedBy="cart",cascade=CascadeType.ALL,orphanRemoval=true)
    // Store the list of related items entries
    private List<CartItem> items=new ArrayList<>();
}
