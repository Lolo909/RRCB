package com.example.rrcb.model.binding;

import com.example.rrcb.model.entity.Category;
import com.example.rrcb.model.entity.Image;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Set;

public class CarAddBindingModel {


//    private String name;
//
//    @Column(nullable = false)
//    private String model;
//
//    @Column(nullable = false)
//    private String brand;
//
//    @Column()
//    private Instant created;
//
//    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
//    private Set<Image> images;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<Category> categories;

    @Size(min = 3, max = 20, message = "Car name must be between 3 and 20 characters!")
    private String name;

    @Size(min = 3)
    private String model;

    @Size(min = 3)
    private String brand;

    private Set<Image> images;

    private Set<CategoryNameEnum> categories;

    public CarAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public CarAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarAddBindingModel setModel(String model) {
        this.model = model;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public CarAddBindingModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Set<Image> getImages() {
        return images;
    }

    public CarAddBindingModel setImages(Set<Image> images) {
        this.images = images;
        return this;
    }

    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public CarAddBindingModel setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
        return this;
    }
}
