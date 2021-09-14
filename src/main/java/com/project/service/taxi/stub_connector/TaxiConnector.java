package com.project.service.taxi.stub_connector;

import com.project.service.taxi.exception.NotFoundException;
import com.project.service.taxi.stub_connector.utils.UtilsGeneration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TaxiConnector implements Connector {
    ConcurrentHashMap<String, HashMap<String, List<?>>> foundTaxiForTheUser = new ConcurrentHashMap<>();

    @Override
    public List<ExpensiveTaxi> connectToExpensiveTaxi(String startAddress, String finishAddress, String userIdentification) {
        List<ExpensiveTaxi> expensiveTaxi = (List<ExpensiveTaxi>) checkingUpToDateData(userIdentification, "expensive", startAddress, finishAddress);

        if (!expensiveTaxi.get(0).getStartLocation().equals(startAddress) || !expensiveTaxi.get(0).getDestination().equals(finishAddress)) {
            foundTaxiForTheUser.get(userIdentification).get("expensive").clear();
            expensiveTaxi = (List<ExpensiveTaxi>) checkingUpToDateData(userIdentification, "expensive", startAddress, finishAddress);
        }
        return expensiveTaxi;
    }

    @Override
    public List<RegularTaxi> connectTohRegularTaxi(String startAddress, String finishAddress, String userIdentification) {
        List<RegularTaxi> regularTaxis = (List<RegularTaxi>) checkingUpToDateData(userIdentification, "regular", startAddress, finishAddress);

        if (!regularTaxis.get(0).getStartLocation().equals(startAddress) || !regularTaxis.get(0).getDestination().equals(finishAddress)) {
            foundTaxiForTheUser.get(userIdentification).get("regular").clear();
            regularTaxis = (List<RegularTaxi>) checkingUpToDateData(userIdentification, "regular", startAddress, finishAddress);
        }
        return regularTaxis;
    }

    @Override
    public List<CheapTaxi> connectTohCheapTaxi(String startAddress, String finishAddress, String userIdentification) {
        List<CheapTaxi> cheapTaxis = (List<CheapTaxi>) checkingUpToDateData(userIdentification, "cheap", startAddress, finishAddress);

        if (!cheapTaxis.get(0).getStartLocation().equals(startAddress) || !cheapTaxis.get(0).getDestination().equals(finishAddress)) {
            foundTaxiForTheUser.get(userIdentification).get("cheap").clear();
            cheapTaxis = (List<CheapTaxi>) checkingUpToDateData(userIdentification, "cheap", startAddress, finishAddress);
        }
        return cheapTaxis;
    }

    public List<?> checkingUpToDateData(String userIdentification, String brand, String startAddress, String finishAddress) {
        if (!foundTaxiForTheUser.containsKey(userIdentification)) {
            foundTaxiForTheUser.put(userIdentification, new HashMap<>());
        }
        if (foundTaxiForTheUser.get(userIdentification).get(brand) == null || foundTaxiForTheUser.get(userIdentification).get(brand).isEmpty()) {
            switch (brand) {
                case "expensive": {
                    List<ExpensiveTaxi> expensiveTaxis = UtilsGeneration.generationExpensiveCar();
                    expensiveTaxis.forEach(el -> {
                        el.setStartLocation(startAddress);
                        el.setDestination(finishAddress);
                    });
                    foundTaxiForTheUser.get(userIdentification).put(brand, expensiveTaxis);
                    return expensiveTaxis;
                }
                case "regular": {
                    List<RegularTaxi> regularTaxis = UtilsGeneration.generationRegularCar();
                    regularTaxis.forEach(el -> {
                        el.setStartLocation(startAddress);
                        el.setDestination(finishAddress);
                    });
                    foundTaxiForTheUser.get(userIdentification).put(brand, regularTaxis);
                    return regularTaxis;
                }
                case "cheap": {
                    List<CheapTaxi> cheapTaxis = UtilsGeneration.generationCheapCar();
                    cheapTaxis.forEach(el -> {
                        el.setStartLocation(startAddress);
                        el.setDestination(finishAddress);
                    });
                    foundTaxiForTheUser.get(userIdentification).put(brand, cheapTaxis);
                    return cheapTaxis;
                }
            }
            throw new NotFoundException();
        }
        return foundTaxiForTheUser.get(userIdentification).get(brand);
    }
}


