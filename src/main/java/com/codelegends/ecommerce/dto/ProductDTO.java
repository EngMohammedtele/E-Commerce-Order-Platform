package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.Product;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.*;
import java.util.*;
// Let Lombok generate simple getters and setters
@Data
// Support building this DTO with chained builder calls
@Builder
// Provide an empty constructor for frameworks and JSON binding
@NoArgsConstructor
// Provide a constructor that receives every DTO field
@AllArgsConstructor
// Moves product data between the API and service layers
public class ProductDTO {
    // Store the id value for this object
    // Expose the database id when this Product is returned
    private Long id;
    // Require the name value to contain text
    @NotBlank
    // Keep the name text inside the allowed length
    @Size(max=120)
    // Store the name value for this object
    // Carry the display name for this Product
    private String name;
    // Make sure the price value is provided
    @NotNull
    // Require the price amount to meet the minimum value
    @DecimalMin("0.01")
    // Store the price value for this object
    // Send the product price as money data
    private BigDecimal price;
    // Make sure the stock quantity value is provided
    @NotNull
    // Allow zero or a positive number for stock quantity
    @PositiveOrZero
    // Store the stockQuantity value for this object
    // Show how many units are available in stock
    private Integer stockQuantity;
    // Require the sku value to contain text
    @NotBlank
    // Keep the sku text inside the allowed length
    @Size(max=50)
    // Store the sku value for this object
    // Carry the product stock keeping code
    private String sku;
    // Make sure the category id value is provided
    @NotNull
    // Validate that the category id value is greater than zero
    @Positive
    // Store the categoryId value for this object
    // Connect the product to its category
    private Long categoryId;
    // Convert one entity object into a DTO
    // Start mapping one database entity into API data
    public static ProductDTO convertToDTO(Product e) {
        // Build and return the DTO with copied values
        // Copy only API-safe fields into the DTO result
        return builder().id(e.getId()).name(e.getName()).price(e.getPrice()).stockQuantity(e.getStockQuantity()).sku(e.getSku()).categoryId(e.getCategory().getId()).build();
    }
    // Convert each entity in the list into a DTO
    public static List<ProductDTO> convertToDTO(List<Product>x) {
        // Return the converted stream results to the caller
        return x.stream().map(ProductDTO::convertToDTO).toList();
    }
}
