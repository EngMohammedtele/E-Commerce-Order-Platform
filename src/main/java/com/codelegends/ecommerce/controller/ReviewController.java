package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.dto.ReviewDTO;
import com.codelegends.ecommerce.service.ReviewService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/reviews")
// Handles web requests for review operations
public class ReviewController extends AbstractCrudController<ReviewDTO> {
    // Keep the service dependency ready for this class
    private final ReviewService service;
    public ReviewController(ReviewService s) {
        super(s);
        service=s;
    }
    @GetMapping("/product/{id}/average-rating")Double average(@PathVariable Long id) {
        return service.average(id);
    }
}
