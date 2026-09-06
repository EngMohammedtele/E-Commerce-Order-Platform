package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.*;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentDTO {
    private Long id;
    @NotBlank
    @Size(max=80)
    private String trackingNumber;
    private Enums.ShipmentStatus status;
    private LocalDateTime shippedDate;
    @NotNull
    @Positive
    private Long orderId;
    public static ShipmentDTO convertToDTO(Shipment e) {
        return builder().id(e.getId()).trackingNumber(e.getTrackingNumber()).status(e.getStatus()).shippedDate(e.getShippedDate()).orderId(e.getOrder().getId()).build();
    }
    public static List<ShipmentDTO> convertToDTO(List<Shipment>x) {
        return x.stream().map(ShipmentDTO::convertToDTO).toList();
    }
}
