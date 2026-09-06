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
public class CartItemService implements CrudService<CartItemDTO> {
    private final CartItemRepository repo;
    public CartItemDTO create(CartItemDTO d) {
        throw new BusinessException("Use the customer cart endpoints");
    }
    public List<CartItemDTO>getAll() {
        return CartItemDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    public CartItemDTO getById(Long id) {
        return CartItemDTO.convertToDTO(find(id));
    }
    public CartItemDTO update(Long id,CartItemDTO d) {
        throw new BusinessException("Use the customer cart endpoints");
    }
    public void delete(Long id) {
        CartItem e=find(id);
        e.setActive(false);
        repo.save(e);
    }
    CartItem find(Long id) {
        return EntityHelper.active(repo,id,"Cart item");
    }
}
