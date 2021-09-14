package com.project.service.taxi.stub_connector;

import java.math.BigDecimal;

public class ExpensiveTaxi {
    private Long id;
    private String modelCar;
    private int yearOfManufacture;
    private String startLocation;
    private String destination;
    private BigDecimal price;
    private String[] services = {
            "Встреча с табличкой",
            "Бесплатное ожидание",
            "предоставление автокресла для ребёнка",
            "содействие в переносе багажа клиента",
            "услуга «трезвый водитель»",
            "транспортное обслуживание свадебных и иных мероприятий"};

    public ExpensiveTaxi() {
    }


    public ExpensiveTaxi(Long id, String modelCar, int yearOfManufacture, BigDecimal price) {
        this.id = id;
        this.modelCar = modelCar;
        this.yearOfManufacture = yearOfManufacture;
        this.price = price;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
