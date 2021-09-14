package com.project.service.taxi.repository;

import com.project.service.taxi.stub_connector.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaxiRepositoryIml implements TaxiRepository {
    private final Connector connector;

    @Autowired
    public TaxiRepositoryIml(TaxiConnector taxiConnector) {
        this.connector = taxiConnector;
    }

    @Override
    public List<ExpensiveTaxi> searchExpensiveTaxi(String startAddress, String finishAddress, String userIdentification) {
        return connector.connectToExpensiveTaxi(startAddress,finishAddress,userIdentification);
    }

    @Override
    public List<RegularTaxi> searchRegularTaxi(String startAddress, String finishAddress, String userIdentification) {
        return connector.connectTohRegularTaxi(startAddress,finishAddress,userIdentification);
    }

    @Override
    public List<CheapTaxi> searchCheapTaxi(String startAddress, String finishAddress, String userIdentification) {
        return connector.connectTohCheapTaxi(startAddress,finishAddress,userIdentification);
    }
}
