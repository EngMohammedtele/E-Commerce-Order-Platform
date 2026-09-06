package com.codelegends.ecommerce.service;
import com.codelegends.ecommerce.dto.*;
import com.codelegends.ecommerce.entity.*;
import com.codelegends.ecommerce.repository.*;
import com.codelegends.ecommerce.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
@RequiredArgsConstructor
@Transactional
public class CartService implements CrudService<CartDTO> {
    private final CartRepository repo;
    private final CartItemRepository items;
    private final CustomerService customers;
    private final ProductService products;
    public CartDTO create(CartDTO d) {
        Cart e=new Cart();
        e.setCustomer(customers.find(d.getCustomerId()));
        return CartDTO.convertToDTO(repo.save(e));
    }
    public List<CartDTO>getAll() {
        return CartDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    public CartDTO getById(Long id) {
        return CartDTO.convertToDTO(find(id));
    }
    public CartDTO update(Long id,CartDTO d) {
        Cart e=find(id);
        e.setCustomer(customers.find(d.getCustomerId()));
        return CartDTO.convertToDTO(repo.save(e));
    }
    public void delete(Long id) {
        Cart e=find(id);
        e.setActive(false);
        repo.save(e);
    }
    Cart find(Long id) {
        return EntityHelper.active(repo,id,"Cart");
    }
    public CartDTO add(Long customerId,Long productId,int quantity) {
        if(quantity<1)throw new BusinessException("Quantity must be positive");
        Cart c=repo.findByCustomerIdAndIsActiveTrue(customerId).orElseThrow(()->new ResourceNotFoundException("Cart not found"));
        Product p=products.find(productId);
        CartItem item=items.findByCartIdAndProductIdAndIsActiveTrue(c.getId(),p.getId()).orElse(null);
        int total=quantity+(item==null?0:item.getQuantity());
        if(total>p.getStockQuantity())throw new BusinessException("Requested quantity exceeds stock");
        if(item==null) {
            item=new CartItem();
            item.setCart(c);
            item.setProduct(p);
        }
        item.setQuantity(total);
        items.save(item);
        return CartDTO.convertToDTO(repo.findById(c.getId()).orElseThrow());
    }
    public CartDTO quantity(Long customerId,Long itemId,int q) {
        if(q<1)throw new BusinessException("Quantity must be positive");
        CartItem i=EntityHelper.active(items,itemId,"Cart item");
        if(!i.getCart().getCustomer().getId().equals(customerId))throw new BusinessException("Item does not belong to customer cart");
        if(q>i.getProduct().getStockQuantity())throw new BusinessException("Requested quantity exceeds stock");
        i.setQuantity(q);
        items.save(i);
        return CartDTO.convertToDTO(i.getCart());
    }
    public void remove(Long customerId,Long itemId) {
        CartItem i=EntityHelper.active(items,itemId,"Cart item");
        if(!i.getCart().getCustomer().getId().equals(customerId))throw new BusinessException("Item does not belong to customer cart");
        i.setActive(false);
        items.save(i);
    }
}
