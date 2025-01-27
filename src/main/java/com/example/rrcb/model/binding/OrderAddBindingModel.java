package com.example.rrcb.model.binding;

import java.util.List;
import java.util.Set;

public class OrderAddBindingModel {

    private List<Integer> allOrderDays;
    private List<String> AllOrderedDaysStringList;

    public OrderAddBindingModel() {
    }

    public List<Integer> getAllOrderDays() {
        return allOrderDays;
    }

    public OrderAddBindingModel setAllOrderDays(List<Integer> allOrderDays) {
        this.allOrderDays = allOrderDays;
        return this;
    }

    public List<String> getAllOrderedDaysStringList() {
        return AllOrderedDaysStringList;
    }

    public OrderAddBindingModel setAllOrderedDaysStringList(List<String> allOrderedDaysStringList) {
        AllOrderedDaysStringList = allOrderedDaysStringList;
        return this;
    }
}
