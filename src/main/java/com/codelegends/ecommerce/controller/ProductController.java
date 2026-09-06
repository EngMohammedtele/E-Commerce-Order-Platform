package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.dto.ProductDTO;
import com.codelegends.ecommerce.service.ProductService;
import com.codelegends.ecommerce.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;
import java.math.*;
import java.util.*;
@RestController
@RequestMapping("/api/products")
public class ProductController extends AbstractCrudController<ProductDTO> {
    private final ProductRepository repo;
    public ProductController(ProductService s,ProductRepository r) {
        super(s);
        repo=r;
    }
    @GetMapping("/category/{id}")List<ProductDTO>category(@PathVariable Long id) {
        return ProductDTO.convertToDTO(repo.findByCategoryIdAndIsActiveTrue(id));
    }
    @GetMapping("/below-price")List<ProductDTO>below(@RequestParam BigDecimal price) {
        return ProductDTO.convertToDTO(repo.findByPriceLessThanAndIsActiveTrue(price));
    }
    @GetMapping("/low-stock")List<ProductDTO>low(@RequestParam Integer threshold) {
        return ProductDTO.convertToDTO(repo.findByStockQuantityLessThanAndIsActiveTrue(threshold));
    }
}
