package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.dto.OrderItemDTO;
import com.codelegends.ecommerce.service.OrderItemService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/order-items")
// Handles web requests for orderitem operations
public class OrderItemController extends AbstractCrudController<OrderItemDTO> {
    public OrderItemController(OrderItemService s) {
        super(s);
    }
}
