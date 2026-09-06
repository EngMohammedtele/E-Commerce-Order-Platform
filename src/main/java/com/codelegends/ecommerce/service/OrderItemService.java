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
public class OrderItemService implements CrudService<OrderItemDTO> {
    private final OrderItemRepository repo;
    public OrderItemDTO create(OrderItemDTO d) {
        throw new BusinessException("Order items are created by placing a cart order");
    }
    public List<OrderItemDTO>getAll() {
        return OrderItemDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    public OrderItemDTO getById(Long id) {
        return OrderItemDTO.convertToDTO(find(id));
    }
    public OrderItemDTO update(Long id,OrderItemDTO d) {
        throw new BusinessException("Order item snapshots cannot be changed");
    }
    public void delete(Long id) {
        OrderItem e=find(id);
        e.setActive(false);
        repo.save(e);
    }
    OrderItem find(Long id) {
        return EntityHelper.active(repo,id,"Order item");
    }
}
