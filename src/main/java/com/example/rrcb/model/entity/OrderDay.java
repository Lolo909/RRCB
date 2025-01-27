package com.example.rrcb.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordered_days")
public class OrderDay extends BaseEntity{

    @Column
    private String dayOrdered;

    @OneToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_id")
    private User user;

    @OneToOne()
    @JoinColumn(name = "car_id")
    private Car car;

    public OrderDay() {
    }

    public String getDayOrdered() {
        return dayOrdered;
    }

    public OrderDay setDayOrdered(String dayOrdered) {
        this.dayOrdered = dayOrdered;
        return this;
    }

    public User getUser() {
        return user;
    }

    public OrderDay setUser(User user) {
        this.user = user;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public OrderDay setCar(Car car) {
        this.car = car;
        return this;
    }
}
