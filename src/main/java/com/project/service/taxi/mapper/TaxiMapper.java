package com.project.service.taxi.mapper;

import com.project.service.taxi.stub_connector.CheapTaxi;
import com.project.service.taxi.stub_connector.ExpensiveTaxi;
import com.project.service.taxi.stub_connector.RegularTaxi;
import com.project.service.taxi.stub_connector.TaxiCar;

public class TaxiMapper {

    public static TaxiCar fromExpensiveToCar(ExpensiveTaxi expensiveTaxi){
        TaxiCar taxiCar = new TaxiCar();
        taxiCar.setId(expensiveTaxi.getId());
        taxiCar.setBrandName("expensive");
        taxiCar.setDestination(expensiveTaxi.getDestination());
        taxiCar.setLocation(expensiveTaxi.getStartLocation());
        taxiCar.setModel(expensiveTaxi.getModelCar());
        taxiCar.setPrice(expensiveTaxi.getPrice());
        taxiCar.setYearOfManufacture(expensiveTaxi.getYearOfManufacture());
        taxiCar.setServices(expensiveTaxi.getServices());
        return taxiCar;
    }
    public static TaxiCar fromRegularToCar(RegularTaxi regularTaxi){
        TaxiCar taxiCar = new TaxiCar();
        taxiCar.setId(regularTaxi.getId());
        taxiCar.setBrandName("regular");
        taxiCar.setLocation(regularTaxi.getStartLocation());
        taxiCar.setDestination(regularTaxi.getDestination());
        taxiCar.setModel(regularTaxi.getModelCar());
        taxiCar.setPrice(regularTaxi.getPrice());
        taxiCar.setYearOfManufacture(regularTaxi.getYearOfManufacture());
        taxiCar.setServices(regularTaxi.getServices());
        return taxiCar;
    }
    public static TaxiCar fromCheapToCar(CheapTaxi cheapTaxi){
        TaxiCar taxiCar = new TaxiCar();
        taxiCar.setId(cheapTaxi.getId());
        taxiCar.setBrandName("cheap");
        taxiCar.setLocation(cheapTaxi.getStartLocation());
        taxiCar.setModel(cheapTaxi.getModelCar());
        taxiCar.setDestination(cheapTaxi.getDestination());
        taxiCar.setPrice(cheapTaxi.getPrice());
        taxiCar.setYearOfManufacture(cheapTaxi.getYearOfManufacture());
        taxiCar.setServices(cheapTaxi.getServices());
        return taxiCar;
    }
}
