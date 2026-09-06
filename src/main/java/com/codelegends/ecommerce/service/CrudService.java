package com.codelegends.ecommerce.service;
import java.util.List;
public interface CrudService<D> {
    D create(D dto);
    List<D> getAll();
    D getById(Long id);
    D update(Long id,D dto);
    void delete(Long id);
}
