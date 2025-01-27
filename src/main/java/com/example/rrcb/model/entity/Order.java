package com.example.rrcb.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @Column
    private LocalDateTime dateTime;

    @Column
    private BigDecimal price;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> allOrderedDays;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    private Car car;

    /*
    @Column(nullable = false)
    private time time;
    */

    public Order() {
    }

    public Order(BigDecimal price, User user, Car car) {
        this.price = price;
        this.user = user;
        this.car = car;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Order setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public Order setCar(Car car) {
        this.car = car;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Order setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public List<Integer> getAllOrderedDays() {
        return allOrderedDays;
    }

    public Order setAllOrderedDays(List<Integer> allOrderedDays) {
        this.allOrderedDays = allOrderedDays;
        return this;
    }
}
