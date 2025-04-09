package com.example.rrcb.service;

import com.example.rrcb.model.binding.CarEditBindingModel;
import com.example.rrcb.model.binding.OrderAddBindingModel;
import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.model.service.CarServiceModel;
import com.example.rrcb.model.view.CarDetailsViewModel;
import com.example.rrcb.model.view.CarRentViewModel;
import com.example.rrcb.model.view.CarViewModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface CarService {
    void addNewCar(CarServiceModel carServiceModel);

    List<CarDetailsViewModel> findAllCarsView();
    Page<CarDetailsViewModel> findAllCarsView(Pageable pageable);


    List<CarViewModel> findAllCarsViewByCategory(CategoryNameEnum categoryNameEnum);
    Page<CarViewModel> findAllCarsViewByCategory(CategoryNameEnum categoryNameEnum, Pageable pageable);

    List<String> findAllUrlS();

    void remove(Long id);


    String findNewestCarImageUrl();

    CarDetailsViewModel findCarById(Long id);

    void editCar(Long id, CarEditBindingModel carEditBindingModel) throws IOException;

    CarRentViewModel findCarForRentById(Long id);

    void rent(Long id, OrderAddBindingModel orderAddBindingModel, Principal principal);

    List<Integer> getAllAvailableDaysMethod(List<Integer> allAvailableDays, List<Integer> allOrderedDays);

    void updateOfCarsAllAvailableDays();

    int getNumberOfDaysInMonth(int year,int month);

    boolean isThereNOTDataAboutAllAvailableDaysInDataBase();

    List<String> getAllOrderedDays(Long id);

    Page<CarDetailsViewModel> searchCars(String search, Pageable pageable);

    Page<CarViewModel> searchCarsByCategory(String search, CategoryNameEnum category, Pageable pageable);



    //void restoreCarAvailableDaysByUserId(Long id);
}
