package com.example.rrcb.model.view;

import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.OrderDay;
import com.example.rrcb.model.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderViewModel {

    private Long id;

    private String dateTime;

    private BigDecimal price;

    private List<OrderDay> allOrderedDays;

    private User user;

    private Car car;

    public OrderViewModel() {
    }

    public Long getId() {
        return id;
    }

    public OrderViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public OrderViewModel setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public List<OrderDay> getAllOrderedDays() {
        return allOrderedDays;
    }

    public OrderViewModel setAllOrderedDays(List<OrderDay> allOrderedDays) {
        this.allOrderedDays = allOrderedDays;
        return this;
    }

    public User getUser() {
        return user;
    }

    public OrderViewModel setUser(User user) {
        this.user = user;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public OrderViewModel setCar(Car car) {
        this.car = car;
        return this;
    }
}
