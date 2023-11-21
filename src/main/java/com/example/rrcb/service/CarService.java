package com.example.rrcb.service;

import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.model.service.CarServiceModel;
import com.example.rrcb.model.view.CarDetailsViewModel;
import com.example.rrcb.model.view.CarViewModel;

import java.util.List;

public interface CarService {
    void addNewCar(CarServiceModel carServiceModel);

    List<CarDetailsViewModel> findAllCarsView();

    List<CarViewModel> findAllCarsViewByCategory(CategoryNameEnum categoryNameEnum);

    List<String> findAllUrlS();

    void remove(Long id);


    String findNewestCarImageUrl();

    CarDetailsViewModel findCarById(Long id);
}
