package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import static com.codelegends.ecommerce.entity.Enums.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
// Defines the Address type used by the ecommerce app
public class Address extends BaseClass {
    @Column(nullable=false,length=200)
    // Store the street value for this object
    private String street;
    @Column(nullable=false,length=100)
    // Store the city value for this object
    private String city;
    @Column(nullable=false,length=20)
    // Store the postalCode value for this object
    private String postalCode;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=10)
    // Store the type value for this object
    private AddressType type;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the customer value for this object
    private Customer customer;
}
