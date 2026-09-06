package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.*;
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
// Moves address data between the API and service layers
public class AddressDTO {
    // Store the id value for this object
    // Expose the database id when this Address is returned
    private Long id;
    // Require the street value to contain text
    @NotBlank
    @Size(max=200)
    // Store the street value for this object
    private String street;
    @NotBlank
    @Size(max=100)
    // Store the city value for this object
    private String city;
    @NotBlank
    @Size(max=20)
    // Store the postalCode value for this object
    private String postalCode;
    @NotNull
    // Store the type value for this object
    private Enums.AddressType type;
    @NotNull
    @Positive
    // Store the customerId value for this object
    private Long customerId;
    // Convert one entity object into a DTO
    public static AddressDTO convertToDTO(Address e) {
        // Build and return the DTO with copied values
        return builder().id(e.getId()).street(e.getStreet()).city(e.getCity()).postalCode(e.getPostalCode()).type(e.getType()).customerId(e.getCustomer().getId()).build();
    }
    // Convert each entity in the list into a DTO
    public static List<AddressDTO> convertToDTO(List<Address>x) {
        // Return the converted stream results to the caller
        return x.stream().map(AddressDTO::convertToDTO).toList();
    }
}
