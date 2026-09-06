package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    @NotBlank
    @Size(max=200)
    private String street;
    @NotBlank
    @Size(max=100)
    private String city;
    @NotBlank
    @Size(max=20)
    private String postalCode;
    @NotNull
    private Enums.AddressType type;
    @NotNull
    @Positive
    private Long customerId;
    public static AddressDTO convertToDTO(Address e) {
        return builder().id(e.getId()).street(e.getStreet()).city(e.getCity()).postalCode(e.getPostalCode()).type(e.getType()).customerId(e.getCustomer().getId()).build();
    }
    public static List<AddressDTO> convertToDTO(List<Address>x) {
        return x.stream().map(AddressDTO::convertToDTO).toList();
    }
}
