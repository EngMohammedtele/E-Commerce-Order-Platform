package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.dto.CategoryDTO;
import com.codelegends.ecommerce.service.CategoryService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/categories")
// Handles web requests for category operations
public class CategoryController extends AbstractCrudController<CategoryDTO> {
    public CategoryController(CategoryService s) {
        super(s);
    }
}
