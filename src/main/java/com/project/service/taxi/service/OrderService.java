package com.project.service.taxi.service;

import com.project.service.taxi.dto.OrderRequestDTO;
import com.project.service.taxi.dto.OrderResponseDTO;
import com.project.service.taxi.entity.Orders;
import com.project.service.taxi.entity.TaxiCar;
import com.project.service.taxi.entity.User;
import com.project.service.taxi.exception.NotFoundException;
import com.project.service.taxi.exception.OrdersException;
import com.project.service.taxi.repository.OrderRepository;
import com.project.service.taxi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TaxiSearchService taxiSearchService;


    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, TaxiSearchService taxiSearchService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.taxiSearchService = taxiSearchService;
    }


    public ResponseEntity<?> createOrder(OrderRequestDTO orderRequestDTO, BindingResult result, Principal principal) throws ExecutionException, InterruptedException {
        ResponseEntity<?> errorMap = validation(result);
        if (errorMap != null) return errorMap;
        Orders orders = new Orders();

        User user = userRepository.findByLogin(principal.getName()).orElseThrow(NotFoundException::new);
        if (user.getOrders() != null) {
          throw new OrdersException();
        }

        TaxiCar car = taxiSearchService.getTaxiByBrand(orderRequestDTO.getBrand(),
                orderRequestDTO.getStartAddress(),
                orderRequestDTO.getEndAddress(),
                principal)
                .stream().filter(el -> el.getId().equals(orderRequestDTO.getCarId())).findFirst().orElseThrow(NotFoundException::new);

        orders.setCarId(orderRequestDTO.getCarId());
        orders.setUser(user);
        orders.setPrice(car.getPrice());
        Long orderId = orderRepository.save(orders).getOrderId();

        return new ResponseEntity<>(new OrderResponseDTO(orderId, "Заказ оформлен",
                orders.getPrice(), car.getModel(),
                orderRequestDTO.getBrand(),
                orderRequestDTO.getStartAddress(),
                orderRequestDTO.getEndAddress(),
                orders.getCreationDate(),
                car.isSoberDriver()), HttpStatus.CREATED);

    }

    public OrderResponseDTO orderCancel(Long orderId) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(NotFoundException::new);
        orderRepository.delete(orders);
        return new OrderResponseDTO(orders.getOrderId(), "Заказ отменен", orders.getPrice(),
                "", "", "", "", new Date(), true);

    }

    private ResponseEntity<?> validation(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }


}
