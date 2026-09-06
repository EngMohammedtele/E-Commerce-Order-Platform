package com.codelegends.ecommerce.dto;
import com.codelegends.ecommerce.entity.Review;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.*;
import java.util.*;
// Let Lombok generate simple getters and setters
@Data
// Support building this DTO with chained builder calls
@Builder
// Provide an empty constructor for frameworks and JSON binding
@NoArgsConstructor
// Provide a constructor that receives every DTO field
@AllArgsConstructor
// Moves review data between the API and service layers
public class ReviewDTO {
    // Store the id value for this object
    // Expose the database id when this Review is returned
    private Long id;
    // Make sure the rating value is provided
    @NotNull
    @Min(1)
    @Max(5)
    // Store the rating value for this object
    private Integer rating;
    @Size(max=1000)
    // Store the comment value for this object
    private String comment;
    @PastOrPresent
    // Store the reviewDate value for this object
    private LocalDateTime reviewDate;
    @NotNull
    @Positive
    // Store the customerId value for this object
    private Long customerId;
    @NotNull
    @Positive
    // Store the productId value for this object
    private Long productId;
    // Convert one entity object into a DTO
    public static ReviewDTO convertToDTO(Review e) {
        // Build and return the DTO with copied values
        return builder().id(e.getId()).rating(e.getRating()).comment(e.getComment()).reviewDate(e.getReviewDate()).customerId(e.getCustomer().getId()).productId(e.getProduct().getId()).build();
    }
    // Convert each entity in the list into a DTO
    public static List<ReviewDTO> convertToDTO(List<Review>x) {
        // Return the converted stream results to the caller
        return x.stream().map(ReviewDTO::convertToDTO).toList();
    }
}
