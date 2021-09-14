package com.project.service.taxi.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class OrderRequestDTO {
    @NotBlank(message = "укажите выбранную вами фирму такси")
    private String brand;
    @Min(value = 0,message = "Укажите привильный carId")
    private Long carId;
    @NotBlank(message = "укажите начальный адрес")
    private String startAddress;
    @NotBlank(message = "укажите конечный адрес")
    private String endAddress;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(String brand,
                           Long carId,
                           String startAddress,
                           String endAddress) {
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
