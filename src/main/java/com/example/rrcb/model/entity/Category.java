package com.example.rrcb.model.entity;


import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Car> cars;

    public Category() {
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public Category setName(CategoryNameEnum name) {
        this.name = name;
        return this;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public Category setCars(Set<Car> cars) {
        this.cars = cars;
        return this;
    }
}
