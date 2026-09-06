package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.math.*;
import static com.codelegends.ecommerce.entity.Enums.*;
// Mark Payment as a table-backed database entity
@Entity
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
// Give JPA an empty constructor to create objects
@NoArgsConstructor
// Defines the Payment type used by the ecommerce app
// Represents the Payment database object in Java
public class Payment extends BaseClass {
    // Require the amount column to have a value
    @Column(nullable=false,precision=12,scale=2)
    // Store the amount value for this object
    // Hold the payment amount saved for the order
    private BigDecimal amount;
    // Save the method enum as readable text
    @Enumerated(EnumType.STRING)
    // Require the method column to have a value
    @Column(nullable=false,length=30)
    // Store the method value for this object
    // Store the selected payment method
    private PaymentMethod method;
    // Save the status enum as readable text
    @Enumerated(EnumType.STRING)
    // Require the status column to have a value
    @Column(nullable=false,length=20)
    // Store the status value for this object
    private PaymentStatus status;
    // Store the paidDate value for this object
    private LocalDateTime paidDate;
    @OneToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(unique=true)
    // Store the order value for this object
    private Order order;
}
