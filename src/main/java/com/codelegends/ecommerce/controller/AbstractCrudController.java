package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.service.CrudService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
// Handles web requests for abstractcrud operations
public abstract class AbstractCrudController<D> {
    // Keep the service dependency ready for this class
    private final CrudService<D> service;
    protected AbstractCrudController(CrudService<D>s) {
        service=s;
    }
    @PostMapping
    // Create a new record from the supplied data
    public ResponseEntity<D>create(@Valid
    @RequestBody D d) {
        // Create a new record from the supplied data
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(d));
    }
    @GetMapping
    // Return all active records for this resource
    public List<D>all() {
        // Return all active records for this resource
        return service.getAll();
    }
    // Find one record using the requested id
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
