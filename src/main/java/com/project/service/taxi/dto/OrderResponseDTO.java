package com.project.service.taxi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.service.taxi.stub_connector.TaxiCar;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderResponseDTO {
    private Long id;
    private String info;
    private BigDecimal price;
    private String modelCar;
    private String brandName;
    private String startAddress;
    private String endAddress;
    @JsonFormat(pattern = "HH:mm:ss dd.MM.yyyy")
    private LocalDateTime dateCreated;
    private String[] services;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Long id, LocalDateTime dateCreated , TaxiCar taxiCar) {
        this.id = id;
        this.info =  "Заказ: №" + id + " на сумму " + taxiCar.getPrice() + "р. оформлен!";
        this.dateCreated = dateCreated;
        this.price = taxiCar.getPrice();
        this.modelCar = taxiCar.getModel();
        this.brandName = taxiCar.getBrandName();
        this.startAddress = taxiCar.getLocation();
        this.endAddress = taxiCar.getDestination();
        this.services = taxiCar.getServices();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }
}
