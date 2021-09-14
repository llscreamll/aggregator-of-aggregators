package com.project.service.taxi.stub_connector;

import java.util.List;

public interface Connector {
    List<ExpensiveTaxi> connectToExpensiveTaxi(String startAddress, String finishAddress, String userIdentification);
    List<RegularTaxi> connectTohRegularTaxi(String startAddress, String finishAddress, String userIdentification);
    List<CheapTaxi> connectTohCheapTaxi(String startAddress, String finishAddress, String userIdentification);
}
