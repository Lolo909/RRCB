package com.example.rrcb.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    /*
    @Column(nullable = false)
    private time time;
    */

    public Order() {
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Order setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Order setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }
}
