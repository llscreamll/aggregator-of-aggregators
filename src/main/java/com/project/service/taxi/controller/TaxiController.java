package com.project.service.taxi.controller;

import com.project.service.taxi.service.TaxiService;
import com.project.service.taxi.stub_connector.TaxiCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
public class TaxiController {
    private final TaxiService taxiService;

    @Autowired
    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @GetMapping("/prices")
    public ResponseEntity<HashMap<String, List<TaxiCar>>> getPrices(@RequestParam("start_place_address") String startAddress,
                                                                    @RequestParam("finish_place_address") String finishAddress,Principal principal) {

        return new ResponseEntity<>(taxiService.findAllTaxi(startAddress, finishAddress, principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/prices/{brand}")
    public ResponseEntity<List<TaxiCar>> getPricesByBrand(@PathVariable("brand") String brandName,
                                                          @RequestParam("start_place_address") String startAddress,
                                                          @RequestParam("finish_place_address") String finishAddress,
                                                          Principal principal){
        return new ResponseEntity<>(taxiService.getTaxiByBrand(brandName, startAddress, finishAddress,principal.getName()), HttpStatus.OK);
    }

}
