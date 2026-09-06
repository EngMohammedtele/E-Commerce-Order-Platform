package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.Store;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Moves store data between the API and service layers
public class StoreDTO {
    // Store the id value for this object
    private Long id;
    @NotBlank
    @Size(max=100)
    // Store the name value for this object
    private String name;
    @NotBlank
    @Size(max=200)
    // Store the location value for this object
    private String location;
    // Convert one entity object into a DTO
    public static StoreDTO convertToDTO(Store e) {
        // Build and return the DTO with copied values
        return builder().id(e.getId()).name(e.getName()).location(e.getLocation()).build();
    }
    // Convert each entity in the list into a DTO
    public static List<StoreDTO> convertToDTO(List<Store> x) {
        // Return the converted stream results to the caller
        return x.stream().map(StoreDTO::convertToDTO).toList();
    }
}
