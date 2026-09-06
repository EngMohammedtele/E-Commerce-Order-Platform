package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.dto.CartItemDTO;
import com.codelegends.ecommerce.service.CartItemService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/cart-items")
// Handles web requests for cartitem operations
public class CartItemController extends AbstractCrudController<CartItemDTO> {
    public CartItemController(CartItemService s) {
        super(s);
    }
}
