package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem extends BaseClass {
    @Column(nullable=false)
    private Integer quantity;
    @Column(nullable=false,precision=12,scale=2)
    private BigDecimal unitPrice;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    private Order order;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    private Product product;
}
