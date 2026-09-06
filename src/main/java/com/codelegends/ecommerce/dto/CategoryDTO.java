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
    @NotBlank
    @Size(max=100)
    // Store the name value for this object
    private String name;
    @Size(max=500)
    // Store the description value for this object
    private String description;
    @NotNull
    @Positive
    // Store the storeId value for this object
    private Long storeId;
    // Convert one entity object into a DTO
    public static CategoryDTO convertToDTO(Category e) {
        // Build and return the DTO with copied values
        return builder().id(e.getId()).name(e.getName()).description(e.getDescription()).storeId(e.getStore().getId()).build();
    }
    // Convert each entity in the list into a DTO
    public static List<CategoryDTO> convertToDTO(List<Category>x) {
        // Return the converted stream results to the caller
        return x.stream().map(CategoryDTO::convertToDTO).toList();
    }
}
