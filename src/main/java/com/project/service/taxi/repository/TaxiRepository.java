package com.project.service.taxi.repository;

import com.project.service.taxi.stub_connector.CheapTaxi;
import com.project.service.taxi.stub_connector.ExpensiveTaxi;
import com.project.service.taxi.stub_connector.RegularTaxi;

import java.util.List;

public interface TaxiRepository {

    List<ExpensiveTaxi> searchExpensiveTaxi(String startAddress, String finishAddress, String userIdentification);
    List<RegularTaxi> searchRegularTaxi(String startAddress, String finishAddress, String userIdentification);
    List<CheapTaxi> searchCheapTaxi(String startAddress, String finishAddress, String userIdentification);

}
