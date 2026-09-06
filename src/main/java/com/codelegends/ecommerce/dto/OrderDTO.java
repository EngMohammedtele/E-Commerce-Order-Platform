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
// Moves order data between the API and service layers
public class OrderDTO {
    // Store the id value for this object
    private Long id;
    @PastOrPresent
    // Store the orderDate value for this object
    private LocalDateTime orderDate;
    // Store the status value for this object
    private Enums.OrderStatus status;
    // Store the totalAmount value for this object
    private BigDecimal totalAmount;
    @NotNull
    @Positive
    // Store the customerId value for this object
    private Long customerId;
    // Store the list of related items entries
    private List<OrderItemDTO> items;
    // Convert one entity object into a DTO
    public static OrderDTO convertToDTO(Order e) {
        // Build and return the DTO with copied values
        return builder().id(e.getId()).orderDate(e.getOrderDate()).status(e.getStatus()).totalAmount(e.getTotalAmount()).customerId(e.getCustomer().getId()).items(OrderItemDTO.convertToDTO(e.getItems())).build();
    }
    // Convert each entity in the list into a DTO
    public static List<OrderDTO> convertToDTO(List<Order>x) {
        // Return the converted stream results to the caller
        return x.stream().map(OrderDTO::convertToDTO).toList();
    }
}
