package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.dto.*;
import com.codelegends.ecommerce.service.*;
import org.springframework.web.bind.annotation.*;
import java.math.*;
@RestController
@RequestMapping("/api/customers")
// Handles web requests for customer operations
public class CustomerController extends AbstractCrudController<CustomerDTO> {
    // Keep the carts dependency ready for this class
    private final CartService carts;
    // Keep the orders dependency ready for this class
    private final OrderService orders;
    public CustomerController(CustomerService s,CartService c,OrderService o) {
        super(s);
        carts=c;
        orders=o;
    }
    @PostMapping("/{customerId}/cart/products/{productId}")CartDTO add(@PathVariable Long customerId,@PathVariable Long productId,@RequestParam int quantity) {
        // Return the result to the calling code
        return carts.add(customerId,productId,quantity);
    }
    @PutMapping("/{customerId}/cart/items/{itemId}")CartDTO quantity(@PathVariable Long customerId,@PathVariable Long itemId,@RequestParam int quantity) {
        return carts.quantity(customerId,itemId,quantity);
    }
    @DeleteMapping("/{customerId}/cart/items/{itemId}")void remove(@PathVariable Long customerId,@PathVariable Long itemId) {
        carts.remove(customerId,itemId);
    }
    @GetMapping("/{id}/orders")java.util.List<OrderDTO>customerOrders(@PathVariable Long id) {
        return orders.customerOrders(id);
    }
    @GetMapping("/{id}/total-spent")BigDecimal spent(@PathVariable Long id) {
        return orders.totalSpent(id);
    }
}
