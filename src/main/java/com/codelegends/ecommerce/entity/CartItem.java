package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
// Mark CartItem as a table-backed database entity
@Entity
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
@NoArgsConstructor
@Table(uniqueConstraints=@UniqueConstraint(columnNames= {
    "cart_id","product_id"
}
// Defines the CartItem type used by the ecommerce app
)) public class CartItem extends BaseClass {
    @Column(nullable=false)
    // Store the quantity value for this object
    private Integer quantity;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the cart value for this object
    private Cart cart;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the product value for this object
    private Product product;
}
