package com.project.service.taxi.stub_connector.utils;

import com.project.service.taxi.entity.TaxiCar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UtilsGeneration {
    private static final String[] modelArray = {"BMV", "Reno", "Audi", "Hyundai", "Mercedes-Benz", "Kia", "Lada", "Ford", "Nissan", "Volkswagen", "Toyota", "Skoda", "Mitsubishi", "Renault"};
    private static final Integer[] yearsModel = {1980, 1996, 2020, 2012, 1998, 1993, 2021, 2007, 2008, 1995, 1992, 1988, 2001, 2003};
    private static final Random random = new Random();

    public static List<TaxiCar> generationCar(String brand, String startAddress, String finishAddress) {
        List<TaxiCar> taxiCars = new ArrayList<>();
        int minCar = 3;
        for (int i = 1; i < random.nextInt(10)+minCar; i++) {
            taxiCars.add(new TaxiCar((long) i,
                    brand.toUpperCase() + "-TAXI",
                    modelArray[random.nextInt(modelArray.length)],
                    yearsModel[random.nextInt(yearsModel.length)],
                    new BigDecimal(random.nextInt(1000)+500),
                    random.nextInt(10) > 2,
                    startAddress,
                    finishAddress
            ));
        }
        return taxiCars;
    }
}
