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

    @OneToMany
    private List<Order> orders;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> allAvailableDays;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> allOrderDays;

    public Car() {
    }

    public List<Integer> getAllOrderDays() {
        return allOrderDays;
    }

    public Car setAllOrderDays(List<Integer> allOrderDays) {
        this.allOrderDays = allOrderDays;
        return this;
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


    public List<Integer> getAllAvailableDays() {
        return allAvailableDays;
    }

    public Car setAllAvailableDays(List<Integer> allAvailableDays) {
        this.allAvailableDays = allAvailableDays;
        return this;
    }


    public String getFile() {
        return file;
    }

    public Car setFile(String file) {
        this.file = file;
        return this;
    }
}
