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
// Moves shipment data between the API and service layers
public class ShipmentDTO {
    // Store the id value for this object
    private Long id;
    @NotBlank
    @Size(max=80)
    // Store the trackingNumber value for this object
    private String trackingNumber;
    // Store the status value for this object
    private Enums.ShipmentStatus status;
    // Store the shippedDate value for this object
    private LocalDateTime shippedDate;
    @NotNull
    @Positive
    // Store the orderId value for this object
    private Long orderId;
    // Convert one entity object into a DTO
    public static ShipmentDTO convertToDTO(Shipment e) {
        // Build and return the DTO with copied values
        return builder().id(e.getId()).trackingNumber(e.getTrackingNumber()).status(e.getStatus()).shippedDate(e.getShippedDate()).orderId(e.getOrder().getId()).build();
    }
    // Convert each entity in the list into a DTO
    public static List<ShipmentDTO> convertToDTO(List<Shipment>x) {
        // Return the converted stream results to the caller
        return x.stream().map(ShipmentDTO::convertToDTO).toList();
    }
}
