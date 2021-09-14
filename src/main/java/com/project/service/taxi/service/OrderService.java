package com.project.service.taxi.service;

import com.project.service.taxi.dto.OrderCanceledDTO;
import com.project.service.taxi.dto.OrderRequestDTO;
import com.project.service.taxi.dto.OrderResponseDTO;
import com.project.service.taxi.entity.Order;
import com.project.service.taxi.entity.User;
import com.project.service.taxi.exception.OrderNotFoundException;
import com.project.service.taxi.exception.TaxiNotFoundException;
import com.project.service.taxi.exception.UserNotFoundException;
import com.project.service.taxi.repository.OrderRepository;
import com.project.service.taxi.repository.UserRepository;
import com.project.service.taxi.stub_connector.TaxiCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TaxiService taxiService;


    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, TaxiService taxiService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.taxiService = taxiService;
    }


    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO, String userIdentification) {
        Order order = new Order();
        User user = userRepository.findByLogin(userIdentification).orElseThrow(UserNotFoundException::new);
        TaxiCar selectedCat = orderCheck(orderRequestDTO, userIdentification);
        order.setCarId(selectedCat.getId());
        order.setCanceledOrder(false);
        order.setUser(user);
        order.setPrice(selectedCat.getPrice());
        Long orderId = orderRepository.save(order).getId();

        return new OrderResponseDTO(orderId, order.getCreationDate(), selectedCat);
    }

    public OrderCanceledDTO orderCancel(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        if (order.getCanceledOrder()) {
            throw new OrderNotFoundException();
        }
        order.setCanceledOrder(true);
        Order save = orderRepository.save(order);
        return new OrderCanceledDTO(save);
    }


    public TaxiCar orderCheck(OrderRequestDTO orderRequestDTO, String userId)  {
        return taxiService.getTaxiByBrand(orderRequestDTO.getBrand(),
                orderRequestDTO.getStartAddress(),
                orderRequestDTO.getEndAddress(),
                userId)
                .stream().filter(el -> el.getId().equals(orderRequestDTO.getCarId())).findFirst().orElseThrow(TaxiNotFoundException::new);
    }
}
