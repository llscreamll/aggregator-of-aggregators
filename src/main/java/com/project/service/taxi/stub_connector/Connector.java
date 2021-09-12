package com.project.service.taxi.stub_connector;

import com.project.service.taxi.entity.TaxiCar;

import java.util.List;

public interface Connector {
    List<TaxiCar> searchYandexTaxi(String startAddress, String finishAddress, String userIdentification);
    List<TaxiCar> searchUberTaxi(String startAddress, String finishAddress, String userIdentification);
    List<TaxiCar> searchGettTaxi(String startAddress, String finishAddress, String userIdentification);
    List<TaxiCar> searchTaxiByBrand(String brand, String startAddress, String finishAddress, String userIdentification);
}
