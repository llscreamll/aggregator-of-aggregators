package com.project.service.taxi.stub_connector.utils;

import com.project.service.taxi.stub_connector.CheapTaxi;
import com.project.service.taxi.stub_connector.ExpensiveTaxi;
import com.project.service.taxi.stub_connector.RegularTaxi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UtilsGeneration {
    private static final String[][] modelCarArray = {
            {"BMV 7", "Audi Q7", "Mercedes-Maybach S-class", " Rolls-Royce Phantom", "Bentley Mulsanne II","Cadillac Escalade"},
            {"Hyundai", "Kia", "Ford", "Nissan", "Skoda","Mazda","Pejo","Volvo"},
            {"LADA", "ВАЗ-21099", "ГАЗ-21", "Москвич 408", "ВАЗ 2115"}
    };
    private static final Random random = new Random();
    private static final int minCar = 2; //1

    public static List<ExpensiveTaxi> generationExpensiveCar() {
        List<ExpensiveTaxi> expensiveTaxi = new ArrayList<>();
        for (int i = 1; i < random.nextInt(10) + minCar; i++) {
            expensiveTaxi.add(
                    new ExpensiveTaxi((long) i,
                            modelCarArray[0][random.nextInt(modelCarArray[0].length)],
                            random.nextInt(5) + 2016,
                            new BigDecimal(random.nextInt(500) + 3000)
                    ));
        }
        return expensiveTaxi;
    }

    public static List<RegularTaxi> generationRegularCar() {
        List<RegularTaxi> regularTaxis = new ArrayList<>();
        for (int i = 1; i < random.nextInt(10) + minCar; i++) {
            regularTaxis.add(
                    new RegularTaxi((long) i,
                            modelCarArray[1][random.nextInt(modelCarArray[1].length)],
                            random.nextInt(10) + 2000,
                            new BigDecimal(random.nextInt(500) + 1500)
                    ));
        }
        return regularTaxis;
    }

    public static List<CheapTaxi> generationCheapCar() {
        List<CheapTaxi> cheapTaxis = new ArrayList<>();
        for (int i = 1; i < random.nextInt(10) + minCar; i++) {
            cheapTaxis.add(
                    new CheapTaxi((long) i,
                            modelCarArray[2][random.nextInt(modelCarArray[2].length)],
                            random.nextInt(10) + 1990,
                            new BigDecimal(random.nextInt(500)+250)
                    ));
        }
        return cheapTaxis;
    }
}
