package com.example.rrcb.model.view;

import com.example.rrcb.model.entity.Image;

import java.time.Instant;
import java.util.Set;

public class CarViewModel {
    private Long id;

    private String name;

    private String model;

    private String brand;

    private Integer created;

    private String imageUrl;

    public CarViewModel() {
    }

    public Long getId() {
        return id;
    }

    public CarViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CarViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarViewModel setModel(String model) {
        this.model = model;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public CarViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Integer getCreated() {
        return created;
    }

    public CarViewModel setCreated(Integer created) {
        this.created = created;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CarViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
