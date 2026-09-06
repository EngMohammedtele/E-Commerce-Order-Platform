package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
// Defines the Store type used by the ecommerce app
public class Store extends BaseClass {
    @Column(nullable=false,length=100)
    // Store the name value for this object
    private String name;
    @Column(nullable=false,length=200)
    // Store the location value for this object
    private String location;
    @OneToMany(mappedBy="store")
    // Store the list of related categories entries
    private List<Category> categories=new ArrayList<>();
    @OneToMany(mappedBy="store")
    // Store the list of related products entries
    private List<Product> products=new ArrayList<>();
    @OneToMany(mappedBy="store")
    // Store the list of related customers entries
    private List<Customer> customers=new ArrayList<>();
}
