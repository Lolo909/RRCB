package com.example.rrcb.model.view;

import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.Order;
import com.example.rrcb.model.entity.User;

public class OrderDayViewModel {
    private Long id;

    private String dayOrdered;

    private Order order;

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

    public Order getOrder() {
        return order;
    }

    public OrderDayViewModel setOrder(Order order) {
        this.order = order;
        return this;
    }
}
