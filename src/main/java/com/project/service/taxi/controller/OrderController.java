package com.project.service.taxi.controller;

import com.project.service.taxi.dto.OrderRequestDTO;
import com.project.service.taxi.dto.OrderResponseDTO;
import com.project.service.taxi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.concurrent.ExecutionException;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@Validated  @RequestBody OrderRequestDTO orderRequestDTO , BindingResult result, Principal principal) throws ExecutionException, InterruptedException {
    return  orderService.createOrder(orderRequestDTO,result,principal) ;
    }

    @PostMapping("/order/cancel/{orderId}")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable("orderId") Long orderId) {
        return  new ResponseEntity<>(orderService.orderCancel(orderId), HttpStatus.OK) ;
    }

}
