package com.project.service.taxi;

import com.project.service.taxi.auth.AuthResponse;
import com.project.service.taxi.controller.AuthAndRegisterController;
import com.project.service.taxi.dto.OrderRequestDTO;
import com.project.service.taxi.dto.OrderResponseDTO;
import com.project.service.taxi.dto.UserRequestDTO;
import com.project.service.taxi.entity.TaxiCar;
import com.project.service.taxi.entity.erole.RoleUser;
import com.project.service.taxi.repository.TaxiRepositoryIml;
import com.project.service.taxi.service.OrderService;
import com.project.service.taxi.service.TaxiSearchService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class TaxiApplicationTests {
    StubPrincipal stubPrincipal = new StubPrincipal();
    Long testOrderId;

    @Autowired
    AuthAndRegisterController authAndRegisterController;
    @Autowired
    TaxiRepositoryIml taxiRepositoryIml;
    @Autowired
    OrderService orderService;
    @Autowired
    TaxiSearchService taxiSearchService;


    @Test
    public void testSaveUserAndReturnToken() {
        ResponseEntity<AuthResponse> authResponseResponseEntity = authAndRegisterController.saveUser(new UserRequestDTO("TestLogin", "testPassword", RoleUser.ROLE_USER));
        Assert.assertEquals(authResponseResponseEntity.getStatusCode(), HttpStatus.CREATED);
        Assert.assertNotEquals(authResponseResponseEntity.getBody().getToken(), "");
    }

    @Test
    public void testSearchGettTaxiAtTheAadress() throws ExecutionException, InterruptedException {
        CompletableFuture<List<TaxiCar>> completableFuture = taxiSearchService.findUberTaxi("Moskow", "Electrostal", stubPrincipal);
        List<TaxiCar> taxiUberList = completableFuture.get();
        Assert.assertNotNull(taxiUberList);
        Assert.assertTrue(taxiUberList.size() >= 1);
        Assert.assertEquals(taxiUberList.get(0).getLocation(), "Moskow");
        Assert.assertEquals(taxiUberList.get(0).getDestination(), "Electrostal");
    }

    @Test
    public void testSearchYandexTaxiAtTheAadress() throws ExecutionException, InterruptedException {
        CompletableFuture<List<TaxiCar>> completableFuture = taxiSearchService.findYandexTaxi("Noginsk", "Balashikha", stubPrincipal);
        List<TaxiCar> taxiYandexList = completableFuture.get();

        Assert.assertNotNull(taxiYandexList);
        Assert.assertTrue(taxiYandexList.size() >= 1);
        Assert.assertEquals(taxiYandexList.get(0).getLocation(), "Noginsk");
        Assert.assertEquals(taxiYandexList.get(0).getDestination(), "Balashikha");
    }

    @Test
    public void testSearchUberTaxiAtTheAadress() throws ExecutionException, InterruptedException {
        CompletableFuture<List<TaxiCar>> completableFuture = taxiSearchService.findGetTaxi("Reutovo", "Zheleznodorozhny", stubPrincipal);
        List<TaxiCar> taxiGettList = completableFuture.get();
        Assert.assertNotNull(taxiGettList);
        Assert.assertTrue(taxiGettList.size() >= 1);
        Assert.assertEquals(taxiGettList.get(0).getLocation(), "Reutovo");
        Assert.assertEquals(taxiGettList.get(0).getDestination(), "Zheleznodorozhny");
    }

    @Test
    public void testOrderCreated() throws ExecutionException, InterruptedException {
        testSaveUserAndReturnToken();
        testSearchUberTaxiAtTheAadress();

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setBrand("uber");
        orderRequestDTO.setCarId(1l);
        orderRequestDTO.setStartAddress("Moskow");
        orderRequestDTO.setEndAddress("Electrostal");

        OrderResponseDTO order = orderService.createOrder(orderRequestDTO, null, stubPrincipal);
        Assert.assertNotNull(orderRequestDTO);
        Assert.assertEquals(order.getText(),"Заказ оформлен");
        Assert.assertEquals(order.getBrandName(),"uber");
        testOrderId = order.getIdOrder();
        Assert.assertTrue(testOrderId != null);
    }

    @Test void removeOrder() throws ExecutionException, InterruptedException {
        testOrderCreated();
        OrderResponseDTO orderResponseDTO = orderService.orderCancel(testOrderId);
        Assert.assertNotNull(orderResponseDTO);
        Assert.assertEquals(orderResponseDTO.getText(),"Заказ отменен");
    }

    @Test()
    void contextLoads() {
    }


}
