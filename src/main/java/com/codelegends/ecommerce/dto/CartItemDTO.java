package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.CartItem;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long id;
    @NotNull
    @Positive
    private Integer quantity;
    @NotNull
    @Positive
    private Long cartId;
    @NotNull
    @Positive
    private Long productId;
    public static CartItemDTO convertToDTO(CartItem e) {
        return builder().id(e.getId()).quantity(e.getQuantity()).cartId(e.getCart().getId()).productId(e.getProduct().getId()).build();
    }
    public static List<CartItemDTO> convertToDTO(List<CartItem>x) {
        return x.stream().map(CartItemDTO::convertToDTO).toList();
    }
}
