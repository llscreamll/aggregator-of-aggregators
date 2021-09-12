package com.project.service.taxi.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(name = "car_id")
    private Long carId;
    @Column(name = "price",columnDefinition = "integer")
    private BigDecimal price;
    @CreationTimestamp
    @Column(name = "creation_date_order")
    private Date creationDate;

    public Orders(Long carId, BigDecimal price,User user) {
        this.carId = carId;
        this.price = price;
        this.user = user;
    }

    public Orders() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", carId=" + carId +
                ", price=" + price +
                ", creationDate=" + creationDate +
                '}';
    }
}
