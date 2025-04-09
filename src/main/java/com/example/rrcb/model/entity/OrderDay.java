package com.example.rrcb.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "ordered_days")
public class OrderDay extends BaseEntity{

    @Column
    private LocalDate dayOrdered;

    @ManyToOne(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public OrderDay() {
    }

    public String getDayOrdered() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dayOrdered.format(formatter);
    }

    public OrderDay setDayOrdered(String dayOrdered) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.dayOrdered = LocalDate.parse(dayOrdered, formatter);
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
