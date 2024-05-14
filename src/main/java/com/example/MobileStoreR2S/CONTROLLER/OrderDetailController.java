package com.example.MobileStoreR2S.CONTROLLER;

import com.example.MobileStoreR2S.DTO.OrderDetailDTO;
import com.example.MobileStoreR2S.SERVICE.OrderDetailSV;
import com.example.MobileStoreR2S.SERVICE.OrderSV;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api/orderDetail")

public class OrderDetailController {
    private final OrderDetailSV orderDetailService;

    public OrderDetailController(OrderDetailSV orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrderDetails() {
        try {
            return ResponseEntity.ok(orderDetailService.findAll());

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(orderDetailService.findById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
