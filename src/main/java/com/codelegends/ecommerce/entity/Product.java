package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints=@UniqueConstraint(columnNames="sku"))
public class Product extends BaseClass {
    @Column(nullable=false,length=120)
    private String name;
    @Column(nullable=false,precision=12,scale=2)
    private BigDecimal price;
    @Column(nullable=false)
    private Integer stockQuantity;
    @Column(nullable=false,length=50)
    private String sku;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    private Category category;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    private Store store;
    @OneToMany(mappedBy="product")
    private List<CartItem> cartItems=new ArrayList<>();
    @OneToMany(mappedBy="product")
    private List<OrderItem> orderItems=new ArrayList<>();
    @OneToMany(mappedBy="product")
    private List<Review> reviews=new ArrayList<>();
}
