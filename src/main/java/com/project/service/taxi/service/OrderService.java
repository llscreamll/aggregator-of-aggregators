package com.project.service.taxi.service;

import com.project.service.taxi.dto.OrderRequestDTO;
import com.project.service.taxi.dto.OrderResponseDTO;
import com.project.service.taxi.entity.Order;
import com.project.service.taxi.entity.TaxiCar;
import com.project.service.taxi.entity.User;
import com.project.service.taxi.exception.NotFoundException;
import com.project.service.taxi.exception.OrdersException;
import com.project.service.taxi.repository.OrderRepository;
import com.project.service.taxi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.security.Principal;
import java.time.LocalDateTime;
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


    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO, BindingResult result, Principal principal) throws ExecutionException, InterruptedException {
        Map<String, String> errorMap = validation(result);
        if (errorMap != null) throw new OrdersException(errorMap.toString());
        Order order = new Order();
        User user = userRepository.findByLogin(principal.getName()).orElseThrow(NotFoundException::new);
        if (user.getOrder() != null) {
          throw new OrdersException("You have active order");
        }

        TaxiCar car = taxiSearchService.getTaxiByBrand(orderRequestDTO.getBrand(),
                orderRequestDTO.getStartAddress(),
                orderRequestDTO.getEndAddress(),
                principal)
                .stream().filter(el -> el.getId().equals(orderRequestDTO.getCarId())).findFirst().orElseThrow(NotFoundException::new);

        order.setCarId(orderRequestDTO.getCarId());
        order.setUser(user);
        order.setPrice(car.getPrice());
        Long orderId = orderRepository.save(order).getOrderId();

        return new OrderResponseDTO(orderId, "Заказ оформлен",
                order.getPrice(), car.getModel(),
                orderRequestDTO.getBrand(),
                orderRequestDTO.getStartAddress(),
                orderRequestDTO.getEndAddress(),
                order.getCreationDate(),
                car.isSoberDriver());

    }

    public OrderResponseDTO orderCancel(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(NotFoundException::new);
        orderRepository.delete(order);
        return new OrderResponseDTO(order.getOrderId(), "Заказ отменен", order.getPrice(),
                "", "", "", "", LocalDateTime.now(), true);

    }

    private Map<String, String> validation(BindingResult result) {
        if (result != null && result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return errorMap;
        }
        return null;
    }


}
