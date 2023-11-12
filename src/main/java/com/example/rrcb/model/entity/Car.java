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

    @Column()
    private Instant created;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    private Set<Image> images;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

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

    public Instant getCreated() {
        return created;
    }

    public Car setCreated(Instant created) {
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

    public Set<Category> getCategories() {
        return categories;
    }

    public Car setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }
}
