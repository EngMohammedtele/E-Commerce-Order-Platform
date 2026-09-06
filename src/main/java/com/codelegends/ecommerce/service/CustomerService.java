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
public class CustomerService implements CrudService<CustomerDTO> {
    private final CustomerRepository repo;
    private final StoreService stores;
    private final CartRepository carts;
    public CustomerDTO create(CustomerDTO d) {
        Customer e=new Customer();
        copy(d,e);
        repo.save(e);
        Cart c=new Cart();
        c.setCustomer(e);
        carts.save(c);
        return CustomerDTO.convertToDTO(e);
    }
    public List<CustomerDTO>getAll() {
        return CustomerDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    public CustomerDTO getById(Long id) {
        return CustomerDTO.convertToDTO(find(id));
    }
    public CustomerDTO update(Long id,CustomerDTO d) {
        Customer e=find(id);
        copy(d,e);
        return CustomerDTO.convertToDTO(repo.save(e));
    }
    public void delete(Long id) {
        Customer e=find(id);
        e.setActive(false);
        repo.save(e);
    }
    Customer find(Long id) {
        return EntityHelper.active(repo,id,"Customer");
    }
    private void copy(CustomerDTO d,Customer e) {
        e.setName(d.getName());
        e.setEmail(d.getEmail());
        e.setPhoneNumber(d.getPhoneNumber());
        e.setGender(d.getGender());
        e.setStore(stores.find(d.getStoreId()));
    }
}
