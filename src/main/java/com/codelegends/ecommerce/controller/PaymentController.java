package com.codelegends.ecommerce.controller;
import com.codelegends.ecommerce.dto.PaymentDTO;
import com.codelegends.ecommerce.service.PaymentService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/payments")
public class PaymentController extends AbstractCrudController<PaymentDTO> {
    public PaymentController(PaymentService s) {
        super(s);
    }
}
