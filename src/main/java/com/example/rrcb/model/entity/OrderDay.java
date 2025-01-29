package com.example.rrcb.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordered_days")
public class OrderDay extends BaseEntity{

    @Column
    private String dayOrdered;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderDay() {
    }

    public String getDayOrdered() {
        return dayOrdered;
    }

    public OrderDay setDayOrdered(String dayOrdered) {
        this.dayOrdered = dayOrdered;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public OrderDay setOrder(Order order) {
        this.order = order;
        return this;
    }
}
