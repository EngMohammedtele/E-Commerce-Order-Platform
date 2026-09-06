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
public class OrderDTO {
    private Long id;
    @PastOrPresent
    private LocalDateTime orderDate;
    private Enums.OrderStatus status;
    private BigDecimal totalAmount;
    @NotNull
    @Positive
    private Long customerId;
    private List<OrderItemDTO> items;
    public static OrderDTO convertToDTO(Order e) {
        return builder().id(e.getId()).orderDate(e.getOrderDate()).status(e.getStatus()).totalAmount(e.getTotalAmount()).customerId(e.getCustomer().getId()).items(OrderItemDTO.convertToDTO(e.getItems())).build();
    }
    public static List<OrderDTO> convertToDTO(List<Order>x) {
        return x.stream().map(OrderDTO::convertToDTO).toList();
    }
}
