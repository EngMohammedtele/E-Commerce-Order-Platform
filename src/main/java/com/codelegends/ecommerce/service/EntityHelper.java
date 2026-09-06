package com.codelegends.ecommerce.service;
import com.codelegends.ecommerce.entity.BaseClass;
import com.codelegends.ecommerce.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.function.*;
final class EntityHelper {
    private EntityHelper() {
    }
    static <E extends BaseClass> E active(JpaRepository<E,Long> repo,Long id,String name) {
        E e=repo.findById(id).orElseThrow(()->new ResourceNotFoundException(name+" not found: "+id));
        if(!e.isActive())throw new ResourceNotFoundException(name+" not found: "+id);
        return e;
    }
}
