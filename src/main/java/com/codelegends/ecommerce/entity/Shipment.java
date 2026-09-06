package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import static com.codelegends.ecommerce.entity.Enums.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints=@UniqueConstraint(columnNames="trackingNumber"))
public class Shipment extends BaseClass {
    @Column(nullable=false,length=80)
    private String trackingNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=20)
    private ShipmentStatus status;
    private LocalDateTime shippedDate;
    @OneToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(unique=true)
    private Order order;
}
