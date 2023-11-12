package com.example.rrcb.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class Image extends BaseEntity{

    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;
    @ManyToOne
    private Car car;
    /*
    @ManyToOne
    private Route route;*/

    public Image() {
    }

    public String getTitle() {
        return title;
    }

    public Image setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Image setUrl(String url) {
        this.url = url;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public Image setCar(Car car) {
        this.car = car;
        return this;
    }
}
