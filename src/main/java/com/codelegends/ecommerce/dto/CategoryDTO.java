package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.Category;
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
// Moves category data between the API and service layers
public class CategoryDTO {
    // Store the id value for this object
    // Expose the database id when this Category is returned
    private Long id;
    // Require the name value to contain text
    @NotBlank
    // Keep the name text inside the allowed length
    @Size(max=100)
    // Store the name value for this object
    // Carry the display name for this Category
    private String name;
    // Keep the description text inside the allowed length
    @Size(max=500)
    // Store the description value for this object
    // Hold optional text that describes the category
    private String description;
    // Make sure the store id value is provided
    @NotNull
    // Validate that the store id value is greater than zero
    @Positive
    // Store the storeId value for this object
    // Connect this DTO to the owning store
    private Long storeId;
    // Convert one entity object into a DTO
    // Start mapping one database entity into API data
    public static CategoryDTO convertToDTO(Category e) {
        // Build and return the DTO with copied values
        // Copy only API-safe fields into the DTO result
        return builder().id(e.getId()).name(e.getName()).description(e.getDescription()).storeId(e.getStore().getId()).build();
    }
    // Convert each entity in the list into a DTO
    // Prepare a DTO list from many database rows
    public static List<CategoryDTO> convertToDTO(List<Category>x) {
        // Return the converted stream results to the caller
        // Map every entity by using the single-item converter
        return x.stream().map(CategoryDTO::convertToDTO).toList();
    }
}
