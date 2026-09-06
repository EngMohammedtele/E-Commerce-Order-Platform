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
)) public class Review extends BaseClass {
    @Column(nullable=false)
    private Integer rating;
    @Column(length=1000)
    private String comment;
    @Column(nullable=false)
    private LocalDateTime reviewDate;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    private Customer customer;
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    private Product product;
}
