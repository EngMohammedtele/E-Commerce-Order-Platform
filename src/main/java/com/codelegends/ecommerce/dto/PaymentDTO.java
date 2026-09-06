package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.*;
import java.math.*;
import java.util.*;
// Let Lombok generate simple getters and setters
@Data
// Support building this DTO with chained builder calls
@Builder
// Provide an empty constructor for frameworks and JSON binding
@NoArgsConstructor
// Provide a constructor that receives every DTO field
@AllArgsConstructor
// Moves payment data between the API and service layers
public class PaymentDTO {
    // Store the id value for this object
    // Expose the database id when this Payment is returned
    private Long id;
    // Validate that the amount value is greater than zero
    @Positive
    // Store the amount value for this object
    // Carry the payment amount for the order
    private BigDecimal amount;
    // Make sure the method value is provided
    @NotNull
    // Store the method value for this object
    // Show how the payment was made
    private Enums.PaymentMethod method;
    // Store the status value for this object
    // Expose the current Payment status
    private Enums.PaymentStatus status;
    // Store the paidDate value for this object
    // Store when the payment was completed
    private LocalDateTime paidDate;
    // Make sure the order id value is provided
    @NotNull
    // Validate that the order id value is greater than zero
    @Positive
    // Store the orderId value for this object
    // Reference the order connected to this data
    private Long orderId;
    // Convert one entity object into a DTO
    // Start mapping one database entity into API data
    public static PaymentDTO convertToDTO(Payment e) {
        // Build and return the DTO with copied values
        return builder().id(e.getId()).amount(e.getAmount()).method(e.getMethod()).status(e.getStatus()).paidDate(e.getPaidDate()).orderId(e.getOrder().getId()).build();
    }
    // Convert each entity in the list into a DTO
    public static List<PaymentDTO> convertToDTO(List<Payment>x) {
        // Return the converted stream results to the caller
        return x.stream().map(PaymentDTO::convertToDTO).toList();
    }
}
