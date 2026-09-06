package com.codelegends.ecommerce.service;
import com.codelegends.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
@RequiredArgsConstructor
// Contains business rules for stats data
public class StatsService {
    // Keep the stores dependency ready for this class
    private final StoreService stores;
    // Keep the products dependency ready for this class
    private final ProductRepository products;
    // Keep the customers dependency ready for this class
    private final CustomerRepository customers;
    // Keep the orders dependency ready for this class
    private final OrderRepository orders;
    // Build summary numbers for one store
    public Map<String,Object> store(Long id) {
        // Load the active entity or fail when missing
        stores.find(id);
        // Fetch only records that are still active
        long p=products.findAllByIsActiveTrue().stream().filter(x->x.getCategory().getStore().getId().equals(id)).count();
        // Fetch only records that are still active
        long c=customers.findAllByIsActiveTrue().stream().filter(x->x.getStore().getId().equals(id)).count();
        // Fetch only records that are still active
        long o=orders.findAllByIsActiveTrue().stream().filter(x->x.getCustomer().getStore().getId().equals(id)).count();
        // Return the calculated statistics as a map
        return Map.of("storeId",id,"activeProducts",p,"activeCustomers",c,"activeOrders",o);
    }
    // Return the product with the highest sales count
    public Object best() {
        // Return the result to the calling code
        return products.bestSelling().map(com.codelegends.ecommerce.dto.ProductDTO::convertToDTO).orElse(null);
    }
}
