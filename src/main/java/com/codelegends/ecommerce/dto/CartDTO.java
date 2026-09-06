package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.Cart;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
// Let Lombok generate simple getters and setters
@Data
// Support building this DTO with chained builder calls
@Builder
// Provide an empty constructor for frameworks and JSON binding
@NoArgsConstructor
// Provide a constructor that receives every DTO field
@AllArgsConstructor
// Moves cart data between the API and service layers
public class CartDTO {
    // Store the id value for this object
    private Long id;
    @NotNull
    @Positive
    // Store the customerId value for this object
    private Long customerId;
    // Store the list of related items entries
    private List<CartItemDTO> items;
    // Convert one entity object into a DTO
    public static CartDTO convertToDTO(Cart e) {
        // Return the converted stream results to the caller
        return builder().id(e.getId()).customerId(e.getCustomer().getId()).items(CartItemDTO.convertToDTO(e.getItems().stream().filter(i->i.isActive()).toList())).build();
    }
    // Convert each entity in the list into a DTO
    public static List<CartDTO> convertToDTO(List<Cart>x) {
        // Return the converted stream results to the caller
        return x.stream().map(CartDTO::convertToDTO).toList();
    }
}
