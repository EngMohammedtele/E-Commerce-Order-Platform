package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.*;
import java.util.*;
// Let Lombok generate simple getters and setters
@Data
// Support building this DTO with chained builder calls
@Builder
// Provide an empty constructor for frameworks and JSON binding
@NoArgsConstructor
// Provide a constructor that receives every DTO field
@AllArgsConstructor
// Moves shipment data between the API and service layers
public class ShipmentDTO {
    // Store the id value for this object
    // Expose the database id when this Shipment is returned
    private Long id;
    // Require the tracking number value to contain text
    @NotBlank
    // Keep the tracking number text inside the allowed length
    @Size(max=80)
    // Store the trackingNumber value for this object
    // Carry the shipment tracking code
    private String trackingNumber;
    // Store the status value for this object
    // Expose the current Shipment status
    private Enums.ShipmentStatus status;
    // Store the shippedDate value for this object
    // Show when the shipment left the store
    private LocalDateTime shippedDate;
    // Make sure the order id value is provided
    @NotNull
    // Validate that the order id value is greater than zero
    @Positive
    // Store the orderId value for this object
    // Reference the order connected to this data
    private Long orderId;
    // Convert one entity object into a DTO
    // Start mapping one database entity into API data
    public static ShipmentDTO convertToDTO(Shipment e) {
        // Build and return the DTO with copied values
        // Copy only API-safe fields into the DTO result
        return builder().id(e.getId()).trackingNumber(e.getTrackingNumber()).status(e.getStatus()).shippedDate(e.getShippedDate()).orderId(e.getOrder().getId()).build();
    }
    // Convert each entity in the list into a DTO
    public static List<ShipmentDTO> convertToDTO(List<Shipment>x) {
        // Return the converted stream results to the caller
        return x.stream().map(ShipmentDTO::convertToDTO).toList();
    }
}
