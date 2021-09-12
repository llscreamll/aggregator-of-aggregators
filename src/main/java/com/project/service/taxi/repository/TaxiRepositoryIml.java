package com.project.service.taxi.repository;

import com.project.service.taxi.entity.TaxiCar;
import com.project.service.taxi.stub_connector.Connector;
import com.project.service.taxi.stub_connector.TaxiConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.List;

@Repository
public class TaxiRepositoryIml implements TaxiRepository {
    private final Connector connector;

    @Autowired
    public TaxiRepositoryIml(TaxiConnector taxiConnector) {
        this.connector = taxiConnector;
    }


    @Override
    public List<TaxiCar> findYandexTaxi(String startAddress, String finishAddress, Principal principal) {
        return connector.searchYandexTaxi(startAddress, finishAddress, principal.getName());
    }

    @Override
    public List<TaxiCar> findUberTaxi(String startAddress, String finishAddress, Principal principal) {
        return connector.searchUberTaxi(startAddress, finishAddress, principal.getName());
    }

    @Override
    public List<TaxiCar> findGettTaxi(String startAddress, String finishAddress, Principal principal) {
        return connector.searchGettTaxi(startAddress, finishAddress, principal.getName());
    }
}
