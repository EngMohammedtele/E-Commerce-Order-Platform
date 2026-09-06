package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints=@UniqueConstraint(columnNames= {
    "cart_id","product_id"
}
)) public class CartItem extends BaseClass {
    @Column(nullable=false)
    private Integer quantity;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    private Cart cart;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    private Product product;
}
