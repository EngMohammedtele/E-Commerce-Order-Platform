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
public class StoreService implements CrudService<StoreDTO> {
    private final StoreRepository repo;
    public StoreDTO create(StoreDTO d) {
        Store e=new Store();
        copy(d,e);
        return StoreDTO.convertToDTO(repo.save(e));
    }
    public List<StoreDTO>getAll() {
        return StoreDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    public StoreDTO getById(Long id) {
        return StoreDTO.convertToDTO(find(id));
    }
    public StoreDTO update(Long id,StoreDTO d) {
        Store e=find(id);
        copy(d,e);
        return StoreDTO.convertToDTO(repo.save(e));
    }
    public void delete(Long id) {
        Store e=find(id);
        e.setActive(false);
        repo.save(e);
    }
    Store find(Long id) {
        return EntityHelper.active(repo,id,"Store");
    }
    private void copy(StoreDTO d,Store e) {
        e.setName(d.getName());
        e.setLocation(d.getLocation());
    }
}
