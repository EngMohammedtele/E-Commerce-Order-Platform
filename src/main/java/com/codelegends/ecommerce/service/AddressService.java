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
public class AddressService implements CrudService<AddressDTO> {
    private final AddressRepository repo;
    private final CustomerService customers;
    public AddressDTO create(AddressDTO d) {
        Address e=new Address();
        copy(d,e);
        return AddressDTO.convertToDTO(repo.save(e));
    }
    public List<AddressDTO>getAll() {
        return AddressDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    public AddressDTO getById(Long id) {
        return AddressDTO.convertToDTO(find(id));
    }
    public AddressDTO update(Long id,AddressDTO d) {
        Address e=find(id);
        copy(d,e);
        return AddressDTO.convertToDTO(repo.save(e));
    }
    public void delete(Long id) {
        Address e=find(id);
        e.setActive(false);
        repo.save(e);
    }
    Address find(Long id) {
        return EntityHelper.active(repo,id,"Address");
    }
    private void copy(AddressDTO d,Address e) {
        e.setStreet(d.getStreet());
        e.setCity(d.getCity());
        e.setPostalCode(d.getPostalCode());
        e.setType(d.getType());
        e.setCustomer(customers.find(d.getCustomerId()));
    }
}
