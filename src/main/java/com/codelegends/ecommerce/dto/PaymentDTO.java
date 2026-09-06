package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.*;
import java.math.*;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long id;
    @Positive
    private BigDecimal amount;
    @NotNull
    private Enums.PaymentMethod method;
    private Enums.PaymentStatus status;
    private LocalDateTime paidDate;
    @NotNull
    @Positive
    private Long orderId;
    public static PaymentDTO convertToDTO(Payment e) {
        return builder().id(e.getId()).amount(e.getAmount()).method(e.getMethod()).status(e.getStatus()).paidDate(e.getPaidDate()).orderId(e.getOrder().getId()).build();
    }
    public static List<PaymentDTO> convertToDTO(List<Payment>x) {
        return x.stream().map(PaymentDTO::convertToDTO).toList();
    }
}
