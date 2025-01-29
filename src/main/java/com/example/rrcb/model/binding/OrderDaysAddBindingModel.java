package com.example.rrcb.model.binding;

import java.util.List;

public class OrderDaysAddBindingModel {

    private List<String> allOrderDaysStringList;

    public OrderDaysAddBindingModel() {
    }

    public List<String> getAllOrderDays() {
        return allOrderDaysStringList;
    }

    public OrderDaysAddBindingModel setAllOrderDays(List<String> allOrderDaysStringList) {
        this.allOrderDaysStringList = allOrderDaysStringList;
        return this;
    }
}
