package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.CartItem;
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
// Moves cartitem data between the API and service layers
public class CartItemDTO {
    // Store the id value for this object
    // Expose the database id when this CartItem is returned
    private Long id;
    // Make sure the quantity value is provided
    @NotNull
    // Validate that the quantity value is greater than zero
    @Positive
    // Store the quantity value for this object
    // Carry how many units are requested
    private Integer quantity;
    // Make sure the cart id value is provided
    @NotNull
    // Validate that the cart id value is greater than zero
    @Positive
    // Store the cartId value for this object
    // Show which cart owns this item
    private Long cartId;
    // Make sure the product id value is provided
    @NotNull
    // Validate that the product id value is greater than zero
    @Positive
    // Store the productId value for this object
    // Identify the product linked to this data
    private Long productId;
    // Convert one entity object into a DTO
    // Start mapping one database entity into API data
    public static CartItemDTO convertToDTO(CartItem e) {
        // Build and return the DTO with copied values
        // Copy only API-safe fields into the DTO result
        return builder().id(e.getId()).quantity(e.getQuantity()).cartId(e.getCart().getId()).productId(e.getProduct().getId()).build();
    }
    // Convert each entity in the list into a DTO
    // Prepare a DTO list from many database rows
    public static List<CartItemDTO> convertToDTO(List<CartItem>x) {
        // Return the converted stream results to the caller
        // Map every entity by using the single-item converter
        return x.stream().map(CartItemDTO::convertToDTO).toList();
    }
}
