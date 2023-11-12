package com.example.rrcb.model.view;

import com.example.rrcb.model.entity.Image;

import java.time.Instant;
import java.util.Set;

public class CarViewModel {
    private Long id;

    private String name;

    private String model;

    private String brand;

    private Instant created;

    private Set<Image> images;

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

    public Instant getCreated() {
        return created;
    }

    public CarViewModel setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Set<Image> getImages() {
        return images;
    }

    public CarViewModel setImages(Set<Image> images) {
        this.images = images;
        return this;
    }
}
