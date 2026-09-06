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
public class OrderItemDTO {
    private Long id;
    @Positive
    private Integer quantity;
    @DecimalMin("0.01")
    private BigDecimal unitPrice;
    @Positive
    private Long orderId;
    @Positive
    private Long productId;
    public static OrderItemDTO convertToDTO(OrderItem e) {
        return builder().id(e.getId()).quantity(e.getQuantity()).unitPrice(e.getUnitPrice()).orderId(e.getOrder().getId()).productId(e.getProduct().getId()).build();
    }
    public static List<OrderItemDTO> convertToDTO(List<OrderItem>x) {
        return x.stream().map(OrderItemDTO::convertToDTO).toList();
    }
}
