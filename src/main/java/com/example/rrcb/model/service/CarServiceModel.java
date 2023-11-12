package com.example.rrcb.model.service;


import com.example.rrcb.model.entity.Category;
import com.example.rrcb.model.entity.Image;
import com.example.rrcb.model.entity.User;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.time.Instant;
import java.util.Set;

public class CarServiceModel {

    private Long id;

    private String name;

    private String model;

    private String brand;

    private Instant created;

    private Set<Image> images;

    private Set<Category> categories;

    public CarServiceModel() {
    }


    public Long getId() {
        return id;
    }

    public CarServiceModel setId(Long id) {
        this.id = id;
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

    public Instant getCreated() {
        return created;
    }

    public CarServiceModel setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Set<Image> getImages() {
        return images;
    }

    public CarServiceModel setImages(Set<Image> images) {
        this.images = images;
        return this;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public CarServiceModel setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }
}
