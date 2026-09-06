package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.dto.StoreDTO;
import com.codelegends.ecommerce.service.StoreService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/stores")
// Handles web requests for store operations
public class StoreController extends AbstractCrudController<StoreDTO> {
    public StoreController(StoreService s) {
        super(s);
    }
}
