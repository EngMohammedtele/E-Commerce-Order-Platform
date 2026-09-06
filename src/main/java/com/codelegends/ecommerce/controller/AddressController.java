package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.dto.AddressDTO;
import com.codelegends.ecommerce.service.AddressService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/addresses")
public class AddressController extends AbstractCrudController<AddressDTO> {
    public AddressController(AddressService s) {
        super(s);
    }
}
