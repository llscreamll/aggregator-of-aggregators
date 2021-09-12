package com.project.service.taxi.dto;

import javax.validation.constraints.NotEmpty;

public class OrderRequestDTO {
    private String brand;
    private Long carId;
    private String startAddress;
    private String endAddress;
    public OrderRequestDTO() {
    }

    public OrderRequestDTO(@NotEmpty(message = "укажите интересующую фирму Такси") String brand,
                           @NotEmpty(message = "укажите индификатор машины") Long carId,
                           @NotEmpty(message = "укажите начальный адрес") String startAddress,
                           @NotEmpty(message = "укажите конечный адрес") String endAddress) {
        this.brand = brand;
        this.carId = carId;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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

    @Override
    public String toString() {
        return "OrderRequestDTO{" +
                "brand='" + brand + '\'' +
                ", carId='" + carId + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                '}';
    }
}
