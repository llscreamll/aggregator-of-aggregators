package com.project.service.taxi.controller;

import com.project.service.taxi.entity.TaxiCar;
import com.project.service.taxi.service.TaxiSearchService;
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
import java.util.concurrent.ExecutionException;

@RestController
public class TaxiController {
    private final TaxiSearchService taxiSearchService;

    @Autowired
    public TaxiController(TaxiSearchService taxiSearchService) {
        this.taxiSearchService = taxiSearchService;
    }

    @GetMapping("/prices")
    public ResponseEntity<HashMap<String, List<TaxiCar>>> getPrices(@RequestParam("start_place_address") String startAddress,
                                                                    @RequestParam("finish_place_address") String finishAddress
            , Principal principal) throws ExecutionException, InterruptedException {

        return new ResponseEntity<>(taxiSearchService.findAllTaxi(startAddress, finishAddress, principal), HttpStatus.OK);
    }

    @GetMapping("/prices/{brand}")
    public ResponseEntity<List<TaxiCar>> getPricesByBrand(@PathVariable("brand") String brandName,
                                                          @RequestParam("start_place_address") String startAddress,
                                                          @RequestParam("finish_place_address") String finishAddress,
                                                          Principal principal) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(taxiSearchService.getTaxiByBrand(brandName, startAddress, finishAddress,principal), HttpStatus.OK);
    }

}
