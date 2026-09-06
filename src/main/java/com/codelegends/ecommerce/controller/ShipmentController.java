package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.dto.ShipmentDTO;
import com.codelegends.ecommerce.service.ShipmentService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/shipments")
public class ShipmentController extends AbstractCrudController<ShipmentDTO> {
    public ShipmentController(ShipmentService s) {
        super(s);
    }
}
