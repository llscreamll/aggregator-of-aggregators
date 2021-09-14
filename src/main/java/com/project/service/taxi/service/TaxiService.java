package com.project.service.taxi.service;

import com.project.service.taxi.exception.NotFoundException;
import com.project.service.taxi.mapper.TaxiMapper;
import com.project.service.taxi.stub_connector.TaxiCar;
import com.project.service.taxi.repository.TaxiRepositoryIml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class TaxiService {
    Logger logger = LoggerFactory.getLogger(TaxiService.class);
    private final TaxiRepositoryIml taxiRepositoryIml;

    @Autowired
    public TaxiService(TaxiRepositoryIml taxiRepositoryIml) {
        this.taxiRepositoryIml = taxiRepositoryIml;
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<TaxiCar>> searchExpensiveTaxi(String startStreet, String endStreet, String userIdentification) {

        return CompletableFuture.completedFuture(taxiRepositoryIml.searchExpensiveTaxi(startStreet, endStreet, userIdentification).stream().map(TaxiMapper::fromExpensiveToCar).collect(Collectors.toList()));
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<TaxiCar>> searchRegularTaxi(String startStreet, String endStreet, String userIdentification) {
        return CompletableFuture.completedFuture(taxiRepositoryIml.searchRegularTaxi(startStreet, endStreet, userIdentification).stream().map(TaxiMapper::fromRegularToCar).collect(Collectors.toList()));
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<TaxiCar>> searchCheapTaxi(String startStreet, String endStreet, String userIdentification) {
        return CompletableFuture.completedFuture(taxiRepositoryIml.searchCheapTaxi(startStreet, endStreet, userIdentification).stream().map(TaxiMapper::fromCheapToCar).collect(Collectors.toList()));
    }

    public HashMap<String, List<TaxiCar>> findAllTaxi(String startAddress, String finishAddress, String userIdentification) {
        HashMap<String, List<TaxiCar>> responseCar = new HashMap<>();

        CompletableFuture<List<TaxiCar>> expensiveTaxi = searchExpensiveTaxi(startAddress, finishAddress, userIdentification);
        CompletableFuture<List<TaxiCar>> regularTaxi = searchRegularTaxi(startAddress, finishAddress, userIdentification);
        CompletableFuture<List<TaxiCar>> cheapTaxi = searchCheapTaxi(startAddress, finishAddress, userIdentification);

        CompletableFuture.allOf(expensiveTaxi, regularTaxi, cheapTaxi).join();

        try {
            responseCar.put("expensive", expensiveTaxi.get());
            responseCar.put("regular", regularTaxi.get());
            responseCar.put("cheap", cheapTaxi.get());
        } catch (ExecutionException | InterruptedException e) {
            logger.error("Error retrieving data");
        }
        return responseCar;
    }


    public List<TaxiCar> getTaxiByBrand(String brandName, String startAddress, String finishAddress, String userIdentification) {
        switch (brandName) {
            case "expensive": {
                try {
                    return searchExpensiveTaxi(startAddress, finishAddress, userIdentification).get();
                } catch (ExecutionException | InterruptedException e) {
                    logger.error("Error retrieving data: Expensive-Taxi");
                }

            }
            case "regular": {
                try {
                    return searchRegularTaxi(startAddress, finishAddress, userIdentification).get();
                } catch (ExecutionException | InterruptedException e) {
                    logger.error("Error retrieving data: Regular-Taxi");
                }
            }
            case "cheap": {
                try {
                    return searchCheapTaxi(startAddress, finishAddress, userIdentification).get();
                } catch (ExecutionException | InterruptedException e) {
                    logger.error("Error retrieving data: Cheap-Taxi");
                }
            }
        }
        throw new NotFoundException();
    }
}
