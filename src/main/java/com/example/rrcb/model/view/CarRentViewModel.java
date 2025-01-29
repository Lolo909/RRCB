package com.example.rrcb.model.view;

import com.example.rrcb.model.entity.Order;
import com.example.rrcb.model.entity.OrderDay;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;

import java.util.List;

public class CarRentViewModel {

    private Long id;

    private String name;

    private String description;

    private String model;

    private String brand;

    private Integer created;

    private String imageUrl;

    private CategoryNameEnum category;

    private List<String> orderedDays;

    public CarRentViewModel() {
    }

    public Long getId() {
        return id;
    }

    public CarRentViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public List<String> getOrderedDays() {
        return orderedDays;
    }

    public CarRentViewModel setOrderedDays(List<String> orderedDays) {
        this.orderedDays = orderedDays;
        return this;
    }

    public String getName() {
        return name;
    }

    public CarRentViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CarRentViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarRentViewModel setModel(String model) {
        this.model = model;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public CarRentViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Integer getCreated() {
        return created;
    }

    public CarRentViewModel setCreated(Integer created) {
        this.created = created;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CarRentViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public CarRentViewModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

}
