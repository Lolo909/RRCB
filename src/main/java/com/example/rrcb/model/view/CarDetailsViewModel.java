package com.example.rrcb.model.view;

import com.example.rrcb.model.entity.Category;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;

public class CarDetailsViewModel {
    private Long id;

    private String name;

    private String description;

    private String model;

    private String brand;

    private Integer created;

    private String imageUrl;

    private CategoryNameEnum category;

    private String file;



    public CarDetailsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public CarDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public CarDetailsViewModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public CarDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CarDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarDetailsViewModel setModel(String model) {
        this.model = model;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public CarDetailsViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Integer getCreated() {
        return created;
    }

    public CarDetailsViewModel setCreated(Integer created) {
        this.created = created;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CarDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getFile() {
        return file;
    }

    public CarDetailsViewModel setFile(String file) {
        this.file = file;
        return this;
    }
}
