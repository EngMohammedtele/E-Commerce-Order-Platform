package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.Category;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    @NotBlank
    @Size(max=100)
    private String name;
    @Size(max=500)
    private String description;
    @NotNull
    @Positive
    private Long storeId;
    public static CategoryDTO convertToDTO(Category e) {
        return builder().id(e.getId()).name(e.getName()).description(e.getDescription()).storeId(e.getStore().getId()).build();
    }
    public static List<CategoryDTO> convertToDTO(List<Category>x) {
        return x.stream().map(CategoryDTO::convertToDTO).toList();
    }
}
