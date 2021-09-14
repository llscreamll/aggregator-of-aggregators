package com.project.service.taxi.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "car_id")
    private Long carId;
    @Column(name = "price",columnDefinition = "integer")
    private BigDecimal price;
    @CreationTimestamp
    @Column(name = "creation_date_order")
    private LocalDateTime creationDate;

    @Column(name = "canceled_order")
    private Boolean canceledOrder;

    public Order(Long id, BigDecimal price, User user,Boolean canceledOrder) {
        this.id = id;
        this.price = price;
        this.user = user;
        this.canceledOrder = canceledOrder;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getCanceledOrder() {
        return canceledOrder;
    }

    public void setCanceledOrder(Boolean canceledOrder) {
        this.canceledOrder = canceledOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", carId=" + carId +
                ", price=" + price +
                ", creation_date=" + creationDate +
                ", canceled_order=" + canceledOrder +
                '}';
    }
}
