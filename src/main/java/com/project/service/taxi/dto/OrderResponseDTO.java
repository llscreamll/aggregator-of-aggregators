package com.project.service.taxi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class OrderResponseDTO {
    private Long idOrder;
    private String text;
    private BigDecimal price;
    private String modelCar;
    private String brandName;
    private String startAddress;
    private String endAddress;
    @JsonFormat(pattern = "HH:mm:ss dd.MM.yyyy")
    private LocalDateTime dateCreated;
    private boolean soberDriver;

    public OrderResponseDTO(Long idOrder, String text,
                            BigDecimal price, String modelCar,
                            String brandName, String startAddress,
                            String endAddress, LocalDateTime dateCreated,
                            boolean soberDriver) {
        this.idOrder = idOrder;
        this.text = text;
        this.price = price;
        this.modelCar = modelCar;
        this.brandName = brandName;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.dateCreated = dateCreated;
        this.soberDriver = soberDriver;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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


    public boolean isSoberDriver() {
        return soberDriver;
    }

    public void setSoberDriver(boolean soberDriver) {
        this.soberDriver = soberDriver;
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
}
