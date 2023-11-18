package com.example.rrcb.model.entity;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Integer created;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    private Set<Image> images;

    @ManyToOne
    private Category category;

    public Car() {
    }

    public String getName() {
        return name;
    }

    public Car setName(String name) {
        this.name = name;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Car setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Integer getCreated() {
        return created;
    }

    public Car setCreated(Integer created) {
        this.created = created;
        return this;
    }

    public Set<Image> getImages() {
        return images;
    }

    public Car setImages(Set<Image> images) {
        this.images = images;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Car setCategory(Category category) {
        this.category = category;
        return this;
    }
}
