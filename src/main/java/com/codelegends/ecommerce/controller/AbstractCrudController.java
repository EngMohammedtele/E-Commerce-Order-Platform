package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.service.CrudService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
public abstract class AbstractCrudController<D> {
    private final CrudService<D> service;
    protected AbstractCrudController(CrudService<D>s) {
        service=s;
    }
    @PostMapping
    public ResponseEntity<D>create(@Valid
    @RequestBody D d) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(d));
    }
    @GetMapping
    public List<D>all() {
        return service.getAll();
    }
    @GetMapping("/{id}")public D one(@PathVariable Long id) {
        return service.getById(id);
    }
    @PutMapping("/{id}")public D update(@PathVariable Long id,@Valid
    @RequestBody D d) {
        return service.update(id,d);
    }
    @DeleteMapping("/{id}")public ResponseEntity<Void>delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
