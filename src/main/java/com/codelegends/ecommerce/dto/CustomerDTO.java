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
// Moves customer data between the API and service layers
public class CustomerDTO {
    // Store the id value for this object
    // Expose the database id when this Customer is returned
    private Long id;
    // Require the name value to contain text
    @NotBlank
    // Keep the name text inside the allowed length
    @Size(max=100)
    // Store the name value for this object
    // Carry the display name for this Customer
    private String name;
    // Require the email value to contain text
    @NotBlank
    // Check that the email has a valid format
    @Email
    // Keep the email text inside the allowed length
    @Size(max=150)
    // Store the email value for this object
    // Hold the customer email sent through the API
    private String email;
    @NotBlank
    @Size(max=20)
    // Store the phoneNumber value for this object
    private String phoneNumber;
    @NotNull
    // Store the gender value for this object
    private Enums.Gender gender;
    @NotNull
    @Positive
    // Store the storeId value for this object
    private Long storeId;
    // Convert one entity object into a DTO
    public static CustomerDTO convertToDTO(Customer e) {
        // Build and return the DTO with copied values
        return builder().id(e.getId()).name(e.getName()).email(e.getEmail()).phoneNumber(e.getPhoneNumber()).gender(e.getGender()).storeId(e.getStore().getId()).build();
    }
    // Convert each entity in the list into a DTO
    public static List<CustomerDTO> convertToDTO(List<Customer>x) {
        // Return the converted stream results to the caller
        return x.stream().map(CustomerDTO::convertToDTO).toList();
    }
}
