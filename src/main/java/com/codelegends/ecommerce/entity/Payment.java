package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.math.*;
import static com.codelegends.ecommerce.entity.Enums.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
// Defines the Payment type used by the ecommerce app
public class Payment extends BaseClass {
    @Column(nullable=false,precision=12,scale=2)
    // Store the amount value for this object
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=30)
    // Store the method value for this object
    private PaymentMethod method;
    @Enumerated(EnumType.STRING)
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
