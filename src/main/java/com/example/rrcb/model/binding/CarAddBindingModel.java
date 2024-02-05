package com.example.rrcb.model.binding;

import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

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

    @Size(min = 10)
    private String description;

    @NotNull
    private String imageUrl;

    @Lob
    @NotNull
    private MultipartFile file;

    @Min(1900)
    private Integer created;

    //private Set<Image> images;

//    @NotNull
//    private CategoryNameEnum category;

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


    public String getImageUrl() {
        return imageUrl;
    }

    public CarAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getCreated() {
        return created;
    }

    public CarAddBindingModel setCreated(Integer created) {
        this.created = created;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CarAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getFile() {
        return file;
    }

    public CarAddBindingModel setFile(MultipartFile file) {
        this.file = file;
        return this;
    }
}
