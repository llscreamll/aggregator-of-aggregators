package com.project.service.taxi.controller;

import com.project.service.taxi.dto.OrderCanceledDTO;
import com.project.service.taxi.dto.OrderRequestDTO;
import com.project.service.taxi.dto.OrderResponseDTO;
import com.project.service.taxi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/order")
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO, Principal principal) {
        return new ResponseEntity<>(orderService.createOrder(orderRequestDTO, principal.getName()), HttpStatus.CREATED);
    }

    @PostMapping("/order/cancel/{orderId}")
    public ResponseEntity<OrderCanceledDTO> cancelOrder(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(orderService.orderCancel(orderId),HttpStatus.OK);
    }

}
