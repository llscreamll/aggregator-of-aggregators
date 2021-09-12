package com.project.service.taxi.service;

import com.project.service.taxi.entity.TaxiCar;
import com.project.service.taxi.exception.NotFoundException;
import com.project.service.taxi.repository.TaxiRepositoryIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class TaxiSearchService {
    private final TaxiRepositoryIml taxiRepositoryIml;

    @Autowired
    public TaxiSearchService(TaxiRepositoryIml taxiRepositoryIml) {
        this.taxiRepositoryIml = taxiRepositoryIml;
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<TaxiCar>> findYandexTaxi(String startStreet, String endStreet, Principal principal) {
        return CompletableFuture.completedFuture(taxiRepositoryIml.findYandexTaxi(startStreet, endStreet, principal));
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<TaxiCar>> findUberTaxi(String startStreet, String endStreet, Principal principal) {
        return CompletableFuture.completedFuture(taxiRepositoryIml.findUberTaxi(startStreet, endStreet, principal));
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<TaxiCar>> findGetTaxi(String startStreet, String endStreet, Principal principal) {
        return CompletableFuture.completedFuture(taxiRepositoryIml.findGettTaxi(startStreet, endStreet, principal));
    }

    public HashMap<String, List<TaxiCar>> findAllTaxi(String startAddress, String finishAddress, Principal principal) throws ExecutionException, InterruptedException {
        HashMap<String, List<TaxiCar>> responseCar = new HashMap<>();


        CompletableFuture<List<TaxiCar>> yandexTaxiList = findYandexTaxi(startAddress, finishAddress, principal);
        CompletableFuture<List<TaxiCar>> gettTaxiList = findGetTaxi(startAddress, finishAddress, principal);
        CompletableFuture<List<TaxiCar>> uberTaxiList = findUberTaxi(startAddress, finishAddress, principal);

        CompletableFuture.allOf(yandexTaxiList, gettTaxiList, uberTaxiList).join();

        responseCar.put("yandex", yandexTaxiList.get());
        responseCar.put("gett", gettTaxiList.get());
        responseCar.put("uber", uberTaxiList.get());
        return responseCar;
    }


    public List<TaxiCar> getTaxiByBrand(String brandName, String startAddress, String finishAddress, Principal principal) throws ExecutionException, InterruptedException {
        switch (brandName) {
            case "yandex": {
                return findYandexTaxi(startAddress, finishAddress, principal).get();
            }
            case "uber": {
                return findUberTaxi(startAddress, finishAddress, principal).get();
            }
            case "gett": {
                return findGetTaxi(startAddress, finishAddress, principal).get();
            }
        }
        throw new NotFoundException();
    }
}
