package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart extends BaseClass {
    @OneToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(unique=true)
    private Customer customer;
    @OneToMany(mappedBy="cart",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<CartItem> items=new ArrayList<>();
}
