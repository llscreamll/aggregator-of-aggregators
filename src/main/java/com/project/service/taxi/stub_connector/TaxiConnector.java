package com.project.service.taxi.stub_connector;

import com.project.service.taxi.entity.TaxiCar;
import com.project.service.taxi.exception.NotFoundException;
import com.project.service.taxi.stub_connector.utils.UtilsGeneration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TaxiConnector implements Connector {
    ConcurrentHashMap<String, ConcurrentHashMap<String, List<TaxiCar>>> foundTaxiForTheUser = new ConcurrentHashMap<>();

    @Override
    public List<TaxiCar> searchYandexTaxi(String startAddress, String finishAddress, String userIdentification) {
        return checkFoundTaxi(startAddress, finishAddress, userIdentification, "yandex");
    }

    @Override
    public List<TaxiCar> searchUberTaxi(String startAddress, String finishAddress, String userIdentification) {
        return checkFoundTaxi(startAddress, finishAddress, userIdentification, "uber");
    }

    @Override
    public List<TaxiCar> searchGettTaxi(String startAddress, String finishAddress, String userIdentification) {
        return checkFoundTaxi(startAddress, finishAddress, userIdentification, "gett");
    }

    @Override
    public List<TaxiCar> searchTaxiByBrand(String brand, String startAddress, String finishAddress, String userIdentification) {
        switch (brand) {
            case "yandex": {
                return checkFoundTaxi(startAddress, finishAddress, userIdentification, "yandex");
            }
            case "uber": {
                return checkFoundTaxi(startAddress, finishAddress, userIdentification, "uber");
            }
            case "gett": {
                return checkFoundTaxi(startAddress, finishAddress, userIdentification, "gett");
            }
        }
        throw new NotFoundException();
    }


    public List<TaxiCar> checkFoundTaxi(String startAddress, String finishAddress, String userIdentification, String brand) {
        if (!foundTaxiForTheUser.containsKey(userIdentification)) {
            foundTaxiForTheUser.put(userIdentification, new ConcurrentHashMap<>());
        }
        if (foundTaxiForTheUser.get(userIdentification).get(brand) == null) {
            foundTaxiForTheUser.get(userIdentification).put(brand, UtilsGeneration.generationCar(brand, startAddress, finishAddress));
        }
        if (!foundTaxiForTheUser.get(userIdentification).get(brand).get(0).getLocation().equals(startAddress) ||
                !foundTaxiForTheUser.get(userIdentification).get(brand).get(0).getDestination().equals(finishAddress)) {
            foundTaxiForTheUser.get(userIdentification).get(brand).clear();
            foundTaxiForTheUser.get(userIdentification).get(brand).addAll(UtilsGeneration.generationCar(brand, startAddress, finishAddress));
        }
        return foundTaxiForTheUser.get(userIdentification).get(brand);
    }
}


