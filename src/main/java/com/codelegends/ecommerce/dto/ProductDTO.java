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
public class ProductDTO {
    private Long id;
    @NotBlank
    @Size(max=120)
    private String name;
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal price;
    @NotNull
    @PositiveOrZero
    private Integer stockQuantity;
    @NotBlank
    @Size(max=50)
    private String sku;
    @NotNull
    @Positive
    private Long categoryId;
    public static ProductDTO convertToDTO(Product e) {
        return builder().id(e.getId()).name(e.getName()).price(e.getPrice()).stockQuantity(e.getStockQuantity()).sku(e.getSku()).categoryId(e.getCategory().getId()).build();
    }
    public static List<ProductDTO> convertToDTO(List<Product>x) {
        return x.stream().map(ProductDTO::convertToDTO).toList();
    }
}
