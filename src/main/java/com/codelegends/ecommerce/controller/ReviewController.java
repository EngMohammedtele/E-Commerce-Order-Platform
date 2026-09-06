package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.dto.ReviewDTO;
import com.codelegends.ecommerce.service.ReviewService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/reviews")
public class ReviewController extends AbstractCrudController<ReviewDTO> {
    private final ReviewService service;
    public ReviewController(ReviewService s) {
        super(s);
        service=s;
    }
    @GetMapping("/product/{id}/average-rating")Double average(@PathVariable Long id) {
        return service.average(id);
    }
}
