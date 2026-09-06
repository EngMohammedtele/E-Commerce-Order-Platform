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
    // Keep the street text inside the allowed length
    @Size(max=200)
    // Store the street value for this object
    // Hold the street line for the address
    private String street;
    // Require the city value to contain text
    @NotBlank
    // Keep the city text inside the allowed length
    @Size(max=100)
    // Store the city value for this object
    // Carry the city name for the address
    private String city;
    // Require the postal code value to contain text
    @NotBlank
    // Keep the postal code text inside the allowed length
    @Size(max=20)
    // Store the postalCode value for this object
    // Keep the postal code used for delivery
    private String postalCode;
    // Make sure the type value is provided
    @NotNull
    // Store the type value for this object
    // Tell whether the address is home, work, or another type
    private Enums.AddressType type;
    // Make sure the customer id value is provided
    @NotNull
    // Validate that the customer id value is greater than zero
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
