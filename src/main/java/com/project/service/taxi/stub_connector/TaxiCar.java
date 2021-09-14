package com.project.service.taxi.stub_connector;

import java.math.BigDecimal;
import java.util.Arrays;

public class TaxiCar {
    private Long id;
    private String brandName;
    private String model;
    private int yearOfManufacture ;
    private BigDecimal price;
    private String location;
    private String destination;
    private String [] services;

    public TaxiCar() {
    }

    public TaxiCar(Long id, String brandName, String model, int yearOfManufacture, BigDecimal price, String location, String destination, String[] services) {
        this.id = id;
        this.brandName = brandName;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.price = price;
        this.location = location;
        this.destination = destination;
        this.services = services;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "TaxiCar{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", model='" + model + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", destination='" + destination + '\'' +
                ", services=" + Arrays.toString(services) +
                '}';
    }
}
