package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.service.StatsService;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/stats")
// Handles web requests for stats operations
public class StatsController {
    // Keep the s dependency ready for this class
    private final StatsService s;
    public StatsController(StatsService s) {
        this.s=s;
    }
    // Build summary numbers for one store
    @GetMapping("/stores/{id}")Map<String,Object>store(@PathVariable Long id) {
        // Build summary numbers for one store
        return s.store(id);
    }
    // Return the product with the highest sales count
    @GetMapping("/best-selling-product")Object best() {
        // Return the product with the highest sales count
        return s.best();
    }
}
