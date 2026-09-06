package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.*;
import java.math.*;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Moves payment data between the API and service layers
public class PaymentDTO {
    // Store the id value for this object
    private Long id;
    @Positive
    // Store the amount value for this object
    private BigDecimal amount;
    @NotNull
    // Store the method value for this object
    private Enums.PaymentMethod method;
    // Store the status value for this object
    private Enums.PaymentStatus status;
    // Store the paidDate value for this object
    private LocalDateTime paidDate;
    @NotNull
    @Positive
    // Store the orderId value for this object
    private Long orderId;
    // Convert one entity object into a DTO
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
