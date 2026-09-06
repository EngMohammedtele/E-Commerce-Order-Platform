package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
// Defines the Category type used by the ecommerce app
public class Category extends BaseClass {
    @Column(nullable=false,length=100)
    // Store the name value for this object
    private String name;
    @Column(length=500)
    // Store the description value for this object
    private String description;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the store value for this object
    private Store store;
    @OneToMany(mappedBy="category")
    // Store the list of related products entries
    private List<Product> products=new ArrayList<>();
}
