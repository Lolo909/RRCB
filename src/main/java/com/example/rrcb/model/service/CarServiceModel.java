package com.example.rrcb.model.service;


import com.example.rrcb.model.entity.Category;

import java.util.List;

public class CarServiceModel {

    private Long id;

    private String name;

    private String description;

    private String model;

    private String brand;

    private Integer created;

    private String imageUrl;

    private String file;

    private Category category;

    private List<Integer> allAvailableDays;

    private List<Integer> allOrderDays;

    public CarServiceModel() {
    }


    public Long getId() {
        return id;
    }

    public CarServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Integer> getAllOrderDays() {
        return allOrderDays;
    }

    public CarServiceModel setAllOrderDays(List<Integer> allOrderDays) {
        this.allOrderDays = allOrderDays;
        return this;
    }

    public List<Integer> getAllAvailableDays() {
        return allAvailableDays;
    }

    public CarServiceModel setAllAvailableDays(List<Integer> allAvailableDays) {
        this.allAvailableDays = allAvailableDays;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public CarServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
