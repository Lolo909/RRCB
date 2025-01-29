package com.example.rrcb.model.entity;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length=10000)
    private String description;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Integer created;

//TODO IN FUTURE ADD MORE IMAGES SOMEHOW
//    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
//    private Set<Image> images;

//    @Column(nullable = false, length=2147483647)
//    private String imageUrl;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String file;

    @ManyToOne
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders;

    public Car() {
    }

    public String getName() {
        return name;
    }

    public Car setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Car setDescription(String description) {
        this.description = description;
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

    public Category getCategory() {
        return category;
    }

    public Car setCategory(Category category) {
        this.category = category;
        return this;
    }


    public String getFile() {
        return file;
    }

    public Car setFile(String file) {
        this.file = file;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Car setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }


}
