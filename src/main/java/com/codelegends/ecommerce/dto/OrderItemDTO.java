package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.OrderItem;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.*;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Moves orderitem data between the API and service layers
public class OrderItemDTO {
    // Store the id value for this object
    private Long id;
    @Positive
    // Store the quantity value for this object
    private Integer quantity;
    @DecimalMin("0.01")
    // Store the unitPrice value for this object
    private BigDecimal unitPrice;
    @Positive
    // Store the orderId value for this object
    private Long orderId;
    @Positive
    // Store the productId value for this object
    private Long productId;
    // Convert one entity object into a DTO
    public static OrderItemDTO convertToDTO(OrderItem e) {
        // Build and return the DTO with copied values
        return builder().id(e.getId()).quantity(e.getQuantity()).unitPrice(e.getUnitPrice()).orderId(e.getOrder().getId()).productId(e.getProduct().getId()).build();
    }
    // Convert each entity in the list into a DTO
    public static List<OrderItemDTO> convertToDTO(List<OrderItem>x) {
        // Return the converted stream results to the caller
        return x.stream().map(OrderItemDTO::convertToDTO).toList();
    }
}
