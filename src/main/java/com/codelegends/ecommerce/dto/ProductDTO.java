package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.Product;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.*;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Moves product data between the API and service layers
public class ProductDTO {
    // Store the id value for this object
    private Long id;
    @NotBlank
    @Size(max=120)
    // Store the name value for this object
    private String name;
    @NotNull
    @DecimalMin("0.01")
    // Store the price value for this object
    private BigDecimal price;
    @NotNull
    @PositiveOrZero
    // Store the stockQuantity value for this object
    private Integer stockQuantity;
    @NotBlank
    @Size(max=50)
    // Store the sku value for this object
    private String sku;
    @NotNull
    @Positive
    // Store the categoryId value for this object
    private Long categoryId;
    // Convert one entity object into a DTO
    public static ProductDTO convertToDTO(Product e) {
        // Build and return the DTO with copied values
        return builder().id(e.getId()).name(e.getName()).price(e.getPrice()).stockQuantity(e.getStockQuantity()).sku(e.getSku()).categoryId(e.getCategory().getId()).build();
    }
    // Convert each entity in the list into a DTO
    public static List<ProductDTO> convertToDTO(List<Product>x) {
        // Return the converted stream results to the caller
        return x.stream().map(ProductDTO::convertToDTO).toList();
    }
}
