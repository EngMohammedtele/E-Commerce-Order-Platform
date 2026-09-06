package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.dto.CartDTO;
import com.codelegends.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/carts")
// Handles web requests for cart operations
public class CartController extends AbstractCrudController<CartDTO> {
    public CartController(CartService s) {
        super(s);
    }
}
