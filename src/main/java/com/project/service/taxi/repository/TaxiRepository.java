package com.project.service.taxi.repository;

import com.project.service.taxi.entity.TaxiCar;

import java.security.Principal;
import java.util.List;

public interface TaxiRepository {

    List<TaxiCar> findYandexTaxi(String startAddress, String finishAddress, String userIdentification) throws InterruptedException;
    List<TaxiCar> findUberTaxi(String startAddress, String finishAddress, String userIdentification) throws InterruptedException;
    List<TaxiCar> findGettTaxi(String startAddress, String finishAddress,String userIdentification) throws InterruptedException;

}
