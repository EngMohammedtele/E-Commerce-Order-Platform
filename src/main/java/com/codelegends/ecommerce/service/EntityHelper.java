package com.codelegends.ecommerce.service;
import com.codelegends.ecommerce.entity.BaseClass;
import com.codelegends.ecommerce.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.function.*;
// Defines the EntityHelper type used by the ecommerce app
final class EntityHelper {
    private EntityHelper() {
    }
    static <E extends BaseClass> E active(JpaRepository<E,Long> repo,Long id,String name) {
        // Build a new object before filling its fields
        E e=repo.findById(id).orElseThrow(()->new ResourceNotFoundException(name+" not found: "+id));
        // Build a new object before filling its fields
        if(!e.isActive())throw new ResourceNotFoundException(name+" not found: "+id);
        // Return the result to the calling code
        return e;
    }
}
