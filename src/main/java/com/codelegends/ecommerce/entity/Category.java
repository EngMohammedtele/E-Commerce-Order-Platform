package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseClass {
    @Column(nullable=false,length=100)
    private String name;
    @Column(length=500)
    private String description;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    private Store store;
    @OneToMany(mappedBy="category")
    private List<Product> products=new ArrayList<>();
}
