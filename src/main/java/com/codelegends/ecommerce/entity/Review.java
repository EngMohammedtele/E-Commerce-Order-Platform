package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints=@UniqueConstraint(columnNames= {
    "customer_id","product_id"
}
// Defines the Review type used by the ecommerce app
)) public class Review extends BaseClass {
    @Column(nullable=false)
    // Store the rating value for this object
    private Integer rating;
    @Column(length=1000)
    // Store the comment value for this object
    private String comment;
    @Column(nullable=false)
    // Store the reviewDate value for this object
    private LocalDateTime reviewDate;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the customer value for this object
    private Customer customer;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    // Store the product value for this object
    private Product product;
}
