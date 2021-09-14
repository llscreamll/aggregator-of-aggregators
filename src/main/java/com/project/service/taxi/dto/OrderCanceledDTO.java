package com.project.service.taxi.dto;

import com.project.service.taxi.entity.Order;

public class OrderCanceledDTO {
    private String info;
    public OrderCanceledDTO(Order order) {
        this.info = "Заказ: №" + order.getId() + " на сумму " + order.getPrice() + "р. отменен!";
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
