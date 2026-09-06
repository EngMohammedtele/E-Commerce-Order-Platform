package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import static com.codelegends.ecommerce.entity.Enums.*;
// Mark Shipment as a table-backed database entity
@Entity
// Let Lombok create getter methods for fields
@Getter
// Let Lombok create setter methods for fields
@Setter
// Give JPA an empty constructor to create objects
@NoArgsConstructor
@Table(uniqueConstraints=@UniqueConstraint(columnNames="trackingNumber"))
// Defines the Shipment type used by the ecommerce app
public class Shipment extends BaseClass {
    @Column(nullable=false,length=80)
    // Store the trackingNumber value for this object
    private String trackingNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=20)
    // Store the status value for this object
    private ShipmentStatus status;
    // Store the shippedDate value for this object
    private LocalDateTime shippedDate;
    @OneToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(unique=true)
    // Store the order value for this object
    private Order order;
}
