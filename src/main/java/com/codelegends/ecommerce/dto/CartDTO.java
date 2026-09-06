package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.Cart;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long id;
    @NotNull
    @Positive
    private Long customerId;
    private List<CartItemDTO> items;
    public static CartDTO convertToDTO(Cart e) {
        return builder().id(e.getId()).customerId(e.getCustomer().getId()).items(CartItemDTO.convertToDTO(e.getItems().stream().filter(i->i.isActive()).toList())).build();
    }
    public static List<CartDTO> convertToDTO(List<Cart>x) {
        return x.stream().map(CartDTO::convertToDTO).toList();
    }
}
