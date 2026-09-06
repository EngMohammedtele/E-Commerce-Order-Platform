package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import static com.codelegends.ecommerce.entity.Enums.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address extends BaseClass {
    @Column(nullable=false,length=200)
    private String street;
    @Column(nullable=false,length=100)
    private String city;
    @Column(nullable=false,length=20)
    private String postalCode;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=10)
    private AddressType type;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    private Customer customer;
}
