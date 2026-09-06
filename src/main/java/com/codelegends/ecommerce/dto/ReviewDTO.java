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
    // Keep the rating at or above the lowest allowed value
    @Min(1)
    // Keep the rating at or below the highest allowed value
    @Max(5)
    // Store the rating value for this object
    // Carry the score chosen by the customer
    private Integer rating;
    // Keep the comment text inside the allowed length
    @Size(max=1000)
    // Store the comment value for this object
    // Hold the optional review text from the customer
    private String comment;
    // Prevent the review date date from being in the future
    @PastOrPresent
    // Store the reviewDate value for this object
    // Show when the review was created
    private LocalDateTime reviewDate;
    // Make sure the customer id value is provided
    @NotNull
    // Validate that the customer id value is greater than zero
    @Positive
    // Store the customerId value for this object
    // Point this DTO back to the related customer
    private Long customerId;
    // Make sure the product id value is provided
    @NotNull
    // Validate that the product id value is greater than zero
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
