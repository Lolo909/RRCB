package com.example.rrcb.model.view;

import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.User;

public class OrderDayViewModel {
    private Long id;

    private String dayOrdered;

    private User user;

    private Car car;

    public OrderDayViewModel() {
    }

    public Long getId() {
        return id;
    }

    public OrderDayViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDayOrdered() {
        return dayOrdered;
    }

    public OrderDayViewModel setDayOrdered(String dayOrdered) {
        this.dayOrdered = dayOrdered;
        return this;
    }

    public User getUser() {
        return user;
    }

    public OrderDayViewModel setUser(User user) {
        this.user = user;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public OrderDayViewModel setCar(Car car) {
        this.car = car;
        return this;
    }
}
