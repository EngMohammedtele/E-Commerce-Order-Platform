package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.service.StatsService;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/stats")
public class StatsController {
    private final StatsService s;
    public StatsController(StatsService s) {
        this.s=s;
    }
    @GetMapping("/stores/{id}")Map<String,Object>store(@PathVariable Long id) {
        return s.store(id);
    }
    @GetMapping("/best-selling-product")Object best() {
        return s.best();
    }
}
