package com.example.rrcb.model.binding;

import com.example.rrcb.model.entity.Category;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class CarEditBindingModel {

    @Size(min = 3, max = 20, message = "Car name must be between 3 and 20 characters!")
    private String name;

    @Size(min = 3)
    private String model;

    @Size(min = 3)
    private String brand;

    @Size(min = 10)
    private String description;


    @Lob
    @NotNull
    private MultipartFile file;

    @Min(1900)
    private Integer created;

    private Category category;

    public CarEditBindingModel() {
    }

    public String getName() {
        return name;
    }

    public CarEditBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public CarEditBindingModel setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarEditBindingModel setModel(String model) {
        this.model = model;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public CarEditBindingModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CarEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getFile() {
        return file;
    }

    public CarEditBindingModel setFile(MultipartFile file) {
        this.file = file;
        return this;
    }

    public Integer getCreated() {
        return created;
    }

    public CarEditBindingModel setCreated(Integer created) {
        this.created = created;
        return this;
    }
}
