package com.codelegends.ecommerce.service;
import com.codelegends.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
@RequiredArgsConstructor
public class StatsService {
    private final StoreService stores;
    private final ProductRepository products;
    private final CustomerRepository customers;
    private final OrderRepository orders;
    public Map<String,Object> store(Long id) {
        stores.find(id);
        long p=products.findAllByIsActiveTrue().stream().filter(x->x.getCategory().getStore().getId().equals(id)).count();
        long c=customers.findAllByIsActiveTrue().stream().filter(x->x.getStore().getId().equals(id)).count();
        long o=orders.findAllByIsActiveTrue().stream().filter(x->x.getCustomer().getStore().getId().equals(id)).count();
        return Map.of("storeId",id,"activeProducts",p,"activeCustomers",c,"activeOrders",o);
    }
    public Object best() {
        return products.bestSelling().map(com.codelegends.ecommerce.dto.ProductDTO::convertToDTO).orElse(null);
    }
}
