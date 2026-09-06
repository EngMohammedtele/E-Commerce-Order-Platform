package com.codelegends.ecommerce.service;
import com.codelegends.ecommerce.dto.*;
import com.codelegends.ecommerce.entity.*;
import com.codelegends.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
@RequiredArgsConstructor
@Transactional
public class ProductService implements CrudService<ProductDTO> {
    private final ProductRepository repo;
    private final CategoryService categories;
    public ProductDTO create(ProductDTO d) {
        Product e=new Product();
        copy(d,e);
        return ProductDTO.convertToDTO(repo.save(e));
    }
    public List<ProductDTO>getAll() {
        return ProductDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    public ProductDTO getById(Long id) {
        return ProductDTO.convertToDTO(find(id));
    }
    public ProductDTO update(Long id,ProductDTO d) {
        Product e=find(id);
        copy(d,e);
        return ProductDTO.convertToDTO(repo.save(e));
    }
    public void delete(Long id) {
        Product e=find(id);
        e.setActive(false);
        repo.save(e);
    }
    Product find(Long id) {
        return EntityHelper.active(repo,id,"Product");
    }
    private void copy(ProductDTO d,Product e) {
        Category c=categories.find(d.getCategoryId());
        e.setName(d.getName());
        e.setPrice(d.getPrice());
        e.setStockQuantity(d.getStockQuantity());
        e.setSku(d.getSku());
        e.setCategory(c);
        e.setStore(c.getStore());
    }
}
