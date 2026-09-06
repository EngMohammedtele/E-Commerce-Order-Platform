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
    // Expose the database id when this Cart is returned
    private Long id;
    // Make sure the customer id value is provided
    @NotNull
    // Validate that the customer id value is greater than zero
    @Positive
    // Store the customerId value for this object
    // Point this DTO back to the related customer
    private Long customerId;
    // Store the list of related items entries
    // Include item DTOs nested inside this response
    private List<CartItemDTO> items;
    // Convert one entity object into a DTO
    // Start mapping one database entity into API data
    public static CartDTO convertToDTO(Cart e) {
        // Return the converted stream results to the caller
        // Copy only API-safe fields into the DTO result
        return builder().id(e.getId()).customerId(e.getCustomer().getId()).items(CartItemDTO.convertToDTO(e.getItems().stream().filter(i->i.isActive()).toList())).build();
    }
    // Convert each entity in the list into a DTO
    // Prepare a DTO list from many database rows
    public static List<CartDTO> convertToDTO(List<Cart>x) {
        // Return the converted stream results to the caller
        // Map every entity by using the single-item converter
        return x.stream().map(CartDTO::convertToDTO).toList();
    }
}
