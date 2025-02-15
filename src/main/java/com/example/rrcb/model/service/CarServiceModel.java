package com.example.rrcb.model.service;


import com.example.rrcb.model.entity.Category;
import com.example.rrcb.model.entity.Order;

import java.util.List;

public class CarServiceModel {

    private Long id;

    private String name;

    private String description;

    private String model;

    private String brand;

    private Integer created;

    private String file;

    private Category category;

    private List<Order> orders;

    public CarServiceModel() {
    }


    public Long getId() {
        return id;
    }

    public CarServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public CarServiceModel setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    public String getName() {
        return name;
    }

    public CarServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarServiceModel setModel(String model) {
        this.model = model;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public CarServiceModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Integer getCreated() {
        return created;
    }

    public CarServiceModel setCreated(Integer created) {
        this.created = created;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public CarServiceModel setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CarServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getFile() {
        return file;
    }

    public CarServiceModel setFile(String file) {
        this.file = file;
        return this;
    }
}
