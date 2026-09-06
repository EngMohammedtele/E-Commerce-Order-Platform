package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.Review;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.*;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    @Size(max=1000)
    private String comment;
    @PastOrPresent
    private LocalDateTime reviewDate;
    @NotNull
    @Positive
    private Long customerId;
    @NotNull
    @Positive
    private Long productId;
    public static ReviewDTO convertToDTO(Review e) {
        return builder().id(e.getId()).rating(e.getRating()).comment(e.getComment()).reviewDate(e.getReviewDate()).customerId(e.getCustomer().getId()).productId(e.getProduct().getId()).build();
    }
    public static List<ReviewDTO> convertToDTO(List<Review>x) {
        return x.stream().map(ReviewDTO::convertToDTO).toList();
    }
}
