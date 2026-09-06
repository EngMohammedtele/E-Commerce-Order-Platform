package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.Store;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {
    private Long id;
    @NotBlank
    @Size(max=100)
    private String name;
    @NotBlank
    @Size(max=200)
    private String location;
    public static StoreDTO convertToDTO(Store e) {
        return builder().id(e.getId()).name(e.getName()).location(e.getLocation()).build();
    }
    public static List<StoreDTO> convertToDTO(List<Store> x) {
        return x.stream().map(StoreDTO::convertToDTO).toList();
    }
}
