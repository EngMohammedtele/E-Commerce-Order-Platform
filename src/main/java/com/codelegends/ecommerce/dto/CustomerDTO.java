package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    @NotBlank
    @Size(max=100)
    private String name;
    @NotBlank
    @Email
    @Size(max=150)
    private String email;
    @NotBlank
    @Size(max=20)
    private String phoneNumber;
    @NotNull
    private Enums.Gender gender;
    @NotNull
    @Positive
    private Long storeId;
    public static CustomerDTO convertToDTO(Customer e) {
        return builder().id(e.getId()).name(e.getName()).email(e.getEmail()).phoneNumber(e.getPhoneNumber()).gender(e.getGender()).storeId(e.getStore().getId()).build();
    }
    public static List<CustomerDTO> convertToDTO(List<Customer>x) {
        return x.stream().map(CustomerDTO::convertToDTO).toList();
    }
}
