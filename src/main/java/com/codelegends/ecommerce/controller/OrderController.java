package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.dto.OrderDTO;
import com.codelegends.ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/orders")
// Handles web requests for order operations
public class OrderController extends AbstractCrudController<OrderDTO> {
    // Keep the service dependency ready for this class
    private final OrderService service;
    public OrderController(OrderService s) {
        super(s);
        service=s;
    }
    @PostMapping("/place/{customerId}")OrderDTO place(@PathVariable Long customerId) {
        // Return the result to the calling code
        return service.place(customerId);
    }
}
