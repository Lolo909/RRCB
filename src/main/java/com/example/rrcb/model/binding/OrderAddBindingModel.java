package com.example.rrcb.model.binding;

import java.util.List;
import java.util.Set;

public class OrderAddBindingModel {

    private List<String> allOrderDays;

    public OrderAddBindingModel() {
    }

    public List<String> getAllOrderDays() {
        return allOrderDays;
    }

    public OrderAddBindingModel setAllOrderDays(List<String> allOrderDays) {
        this.allOrderDays = allOrderDays;
        return this;
    }

}
