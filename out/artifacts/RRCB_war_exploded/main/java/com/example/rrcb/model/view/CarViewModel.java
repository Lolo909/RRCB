package com.example.rrcb.model.view;

public class CarViewModel {

    private Long id;

    private String name;

    private String model;

    private String brand;

    private Integer created;


    private String file;

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

    public Integer getCreated() {
        return created;
    }

    public CarViewModel setCreated(Integer created) {
        this.created = created;
        return this;
    }


    public String getFile() {
        return file;
    }

    public CarViewModel setFile(String file) {
        this.file = file;
        return this;
    }
}
