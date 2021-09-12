package com.project.service.taxi;

import com.project.service.taxi.auth.AuthResponse;
import com.project.service.taxi.controller.AuthAndRegisterController;
import com.project.service.taxi.controller.TaxiController;
import com.project.service.taxi.dto.UserRequestDTO;
import com.project.service.taxi.entity.TaxiCar;
import com.project.service.taxi.entity.erole.RoleUser;
import com.project.service.taxi.service.TaxiSearchService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class TaxiApplicationTests {
    @Autowired
    AuthAndRegisterController authAndRegisterController;

    @Autowired
    TaxiRepo taxiSearchService;


    @Test
    public void testSaveUser(){
        ResponseEntity<AuthResponse> authResponseResponseEntity = authAndRegisterController.saveUser(new UserRequestDTO("TestUser", "testest", RoleUser.ROLE_USER));
        Assert.assertEquals(authResponseResponseEntity.getStatusCode(), HttpStatus.CREATED);
        Assert.assertNotEquals(authResponseResponseEntity.getBody().getToken(),"");
    }

    @Test
    public void testTaxiPrices() {
        taxiSearchService.findAllTaxi("Moscow","Saint Petersburg","")
        Assert.assertTrue(prices.getBody().get(0).size() >=1);
        Assert.assertTrue(prices.getBody().get(1).size() >=1);
        Assert.assertTrue(prices.getBody().get(2).size() >=1);
    }


    @Test
    void contextLoads() {
    }


}
