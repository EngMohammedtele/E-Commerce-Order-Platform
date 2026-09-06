package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import static com.codelegends.ecommerce.entity.Enums.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class Customer extends BaseClass {
    @Column(nullable=false,length=100)
    private String name;
    @Column(nullable=false,length=150)
    private String email;
    @Column(nullable=false,length=20)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=10)
    private Gender gender;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    private Store store;
    @OneToOne(mappedBy="customer")
    private Cart cart;
    @OneToMany(mappedBy="customer")
    private List<Address> addresses=new ArrayList<>();
    @OneToMany(mappedBy="customer")
    private List<Order> orders=new ArrayList<>();
    @OneToMany(mappedBy="customer")
    private List<Review> reviews=new ArrayList<>();
}
