package com.project.service.taxi.stub_connector;

import java.math.BigDecimal;

public class CheapTaxi {
    private Long id;
    private String modelCar;
    private int yearOfManufacture;
    private BigDecimal price;
    private String startLocation;
    private String destination;
    private String[] services = {};

    public CheapTaxi() {
    }

    public CheapTaxi(Long id, String modelCar, int yearOfManufacture, BigDecimal price) {
        this.id = id;
        this.modelCar = modelCar;
        this.yearOfManufacture = yearOfManufacture;
        this.price = price;
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
}
