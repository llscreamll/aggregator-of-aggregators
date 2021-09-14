package com.project.service.taxi;

import com.project.service.taxi.auth.AuthRequest;
import com.project.service.taxi.auth.AuthResponse;
import com.project.service.taxi.controller.AuthAndRegisterController;
import com.project.service.taxi.dto.OrderCanceledDTO;
import com.project.service.taxi.dto.OrderRequestDTO;
import com.project.service.taxi.dto.OrderResponseDTO;
import com.project.service.taxi.dto.UserRequestDTO;
import com.project.service.taxi.exception.UserNotFoundException;
import com.project.service.taxi.stub_connector.TaxiCar;
import com.project.service.taxi.entity.erole.RoleUser;
import com.project.service.taxi.service.OrderService;
import com.project.service.taxi.service.TaxiService;
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
   private Long testOrderId;

    @Autowired
    AuthAndRegisterController authAndRegisterController;
    @Autowired
    OrderService orderService;
    @Autowired
    TaxiService taxiService;


    @Test
    public void testSaveUserAndReturnToken() {
        ResponseEntity<AuthResponse> authResponseResponseEntity = authAndRegisterController.saveUser(new UserRequestDTO("TestLogin", "testPassword", RoleUser.ROLE_USER));
        Assert.assertEquals(authResponseResponseEntity.getStatusCode(), HttpStatus.CREATED);
        Assert.assertNotEquals(authResponseResponseEntity.getBody().getToken(), "");
    }

    @Test
    public void testShouldDiscardErrorThatUserNotFound() {
        Assert.assertThrows(UserNotFoundException.class, () -> authAndRegisterController.checkLoginData(new AuthRequest("loginText", "passwordTest")));
    }

    @Test
    public void testSearchExpensiveTaxiAtTheAddress() throws ExecutionException, InterruptedException {
        CompletableFuture<List<TaxiCar>> completableFuture = taxiService.searchExpensiveTaxi("Moskow", "Electrostal", "TestLogin");
        List<TaxiCar> taxiUberList = completableFuture.get();
        Assert.assertNotNull(taxiUberList);
        Assert.assertTrue(taxiUberList.size() >= 1);
        Assert.assertEquals(taxiUberList.get(0).getLocation(), "Moskow");
        Assert.assertEquals(taxiUberList.get(0).getDestination(), "Electrostal");
    }

    @Test
    public void testSearchRegularTaxiAtTheAddress() throws ExecutionException, InterruptedException {
        CompletableFuture<List<TaxiCar>> completableFuture = taxiService.searchRegularTaxi("Noginsk", "Balashikha", "TestLogin");
        List<TaxiCar> taxiYandexList = completableFuture.get();

        Assert.assertNotNull(taxiYandexList);
        Assert.assertTrue(taxiYandexList.size() >= 1);
        Assert.assertEquals(taxiYandexList.get(0).getLocation(), "Noginsk");
        Assert.assertEquals(taxiYandexList.get(0).getDestination(), "Balashikha");
    }

    @Test
    public void testSearchCheapTaxiAtTheAddress() throws ExecutionException, InterruptedException {
        CompletableFuture<List<TaxiCar>> completableFuture = taxiService.searchExpensiveTaxi("Reutovo", "Zheleznodorozhny", "TestLogin");
        List<TaxiCar> taxiExpensiveList = completableFuture.get();
        Assert.assertNotNull(taxiExpensiveList);
        Assert.assertTrue(taxiExpensiveList.size() >= 1);
        Assert.assertEquals(taxiExpensiveList.get(0).getLocation(), "Reutovo");
        Assert.assertEquals(taxiExpensiveList.get(0).getDestination(), "Zheleznodorozhny");
    }

    @Test
    public void testOrderCreated() throws ExecutionException, InterruptedException {
        testSaveUserAndReturnToken();
        testSearchExpensiveTaxiAtTheAddress();

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setBrand("expensive");
        orderRequestDTO.setCarId(1l);
        orderRequestDTO.setStartAddress("Moskow");
        orderRequestDTO.setEndAddress("Electrostal");

        OrderResponseDTO order = orderService.createOrder(orderRequestDTO, "TestLogin");
        Assert.assertNotNull(orderRequestDTO);
        Assert.assertEquals(order.getBrandName(),"expensive");
        testOrderId = order.getId();
        Assert.assertTrue(testOrderId != null);
    }

    @Test void testRemoveOrder() throws ExecutionException, InterruptedException {
        testOrderCreated();
        OrderCanceledDTO orderCanceledDTO = orderService.orderCancel(testOrderId);
        Assert.assertTrue(orderCanceledDTO.getInfo().contains("отменен"));
    }

}
