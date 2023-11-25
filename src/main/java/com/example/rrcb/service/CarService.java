package com.example.rrcb.service;

import com.example.rrcb.model.binding.CarEditBindingModel;
import com.example.rrcb.model.binding.OrderAddBindingModel;
import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.model.service.CarServiceModel;
import com.example.rrcb.model.view.CarDetailsViewModel;
import com.example.rrcb.model.view.CarRentViewModel;
import com.example.rrcb.model.view.CarViewModel;

import java.security.Principal;
import java.util.List;

public interface CarService {
    void addNewCar(CarServiceModel carServiceModel);

    List<CarDetailsViewModel> findAllCarsView();

    List<CarViewModel> findAllCarsViewByCategory(CategoryNameEnum categoryNameEnum);

    List<String> findAllUrlS();

    void remove(Long id);


    String findNewestCarImageUrl();

    CarDetailsViewModel findCarById(Long id);

    void editCar(Long id, CarEditBindingModel carEditBindingModel);

    

    CarRentViewModel findCarForRentById(Long id);

    void rent(Long id, OrderAddBindingModel orderAddBindingModel, Principal principal);

    List<Integer> getAllAvailableDaysMethod(List<Integer> allAvailableDays, List<Integer> allOrderedDays);
}
