package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Store extends BaseClass {
    @Column(nullable=false,length=100)
    private String name;
    @Column(nullable=false,length=200)
    private String location;
    @OneToMany(mappedBy="store")
    private List<Category> categories=new ArrayList<>();
    @OneToMany(mappedBy="store")
    private List<Product> products=new ArrayList<>();
    @OneToMany(mappedBy="store")
    private List<Customer> customers=new ArrayList<>();
}
