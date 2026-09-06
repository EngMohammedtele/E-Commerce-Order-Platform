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
public class Payment extends BaseClass {
    @Column(nullable=false,precision=12,scale=2)
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=30)
    private PaymentMethod method;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=20)
    private PaymentStatus status;
    private LocalDateTime paidDate;
    @OneToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(unique=true)
    private Order order;
}
