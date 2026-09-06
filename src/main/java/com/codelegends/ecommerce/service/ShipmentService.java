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
public class ShipmentService implements CrudService<ShipmentDTO> {
    private final ShipmentRepository repo;
    public ShipmentDTO create(ShipmentDTO d) {
        throw new BusinessException("Shipment is created automatically after payment");
    }
    public List<ShipmentDTO>getAll() {
        return ShipmentDTO.convertToDTO(repo.findAllByIsActiveTrue());
    }
    public ShipmentDTO getById(Long id) {
        return ShipmentDTO.convertToDTO(find(id));
    }
    public ShipmentDTO update(Long id,ShipmentDTO d) {
        Shipment s=find(id);
        if(d.getStatus()!=null) {
            s.setStatus(d.getStatus());
            if(d.getStatus()==Enums.ShipmentStatus.SHIPPED)s.setShippedDate(LocalDateTime.now());
        }
        return ShipmentDTO.convertToDTO(repo.save(s));
    }
    public void delete(Long id) {
        Shipment e=find(id);
        e.setActive(false);
        repo.save(e);
    }
    Shipment find(Long id) {
        return EntityHelper.active(repo,id,"Shipment");
    }
}
