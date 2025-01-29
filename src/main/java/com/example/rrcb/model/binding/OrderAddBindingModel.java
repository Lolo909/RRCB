package com.example.rrcb.model.binding;

import java.util.List;
import java.util.Set;

public class OrderAddBindingModel {

    private List<Integer> allOrderDays;

    public OrderAddBindingModel() {
    }

    public List<Integer> getAllOrderDays() {
        return allOrderDays;
    }

    public OrderAddBindingModel setAllOrderDays(List<Integer> allOrderDays) {
        this.allOrderDays = allOrderDays;
        return this;
    }

}
