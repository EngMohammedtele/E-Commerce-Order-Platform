package com.codelegends.ecommerce.service;
import com.codelegends.ecommerce.dto.*;
import com.codelegends.ecommerce.entity.*;
import com.codelegends.ecommerce.repository.*;
import com.codelegends.ecommerce.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.time.*;
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService implements CrudService<ReviewDTO> {
    private final ReviewRepository repo;
    private final OrderItemRepository orderItems;
    private final CustomerService customers;
    private final ProductService products;
    public ReviewDTO create(ReviewDTO d) {
        Customer c=customers.find(d.getCustomerId());
        Product p=products.find(d.getProductId());
        if(!orderItems.existsByOrderCustomerIdAndProductIdAndOrderIsActiveTrue(c.getId(),p.getId()))throw new BusinessException("Customer never ordered this product");
        if(repo.existsByCustomerIdAndProductIdAndIsActiveTrue(c.getId(),p.getId()))throw new BusinessException("Customer already reviewed this product");
        Review r=new Review();
        r.setCustomer(c);
        r.setProduct(p);
        r.setRating(d.getRating());
        r.setComment(d.getComment());
        r.setReviewDate(LocalDateTime.now());
        return ReviewDTO.convertToDTO(repo.save(r));
    }
    public List<ReviewDTO>getAll() {
        return ReviewDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    public ReviewDTO getById(Long id) {
        return ReviewDTO.convertToDTO(find(id));
    }
    public ReviewDTO update(Long id,ReviewDTO d) {
        Review r=find(id);
        r.setRating(d.getRating());
        r.setComment(d.getComment());
        return ReviewDTO.convertToDTO(repo.save(r));
    }
    public void delete(Long id) {
        Review e=find(id);
        e.setActive(false);
        repo.save(e);
    }
    Review find(Long id) {
        return EntityHelper.active(repo,id,"Review");
    }
    public Double average(Long productId) {
        products.find(productId);
        return repo.averageRating(productId);
    }
}
