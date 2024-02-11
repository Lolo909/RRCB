package com.example.rrcb.service;

import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.Category;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.model.service.CarServiceModel;
import com.example.rrcb.repository.CarRepository;
import com.example.rrcb.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CarServiceImplTestIT {
    @Autowired
    private CarService carServiceToTest;

    @BeforeEach
    void setUp(){
        carRepository.deleteAll();
    }

    @AfterEach
    void tearDown(){
        carRepository.deleteAll();
    }

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    void testAddingNewCar(){

        List<Integer> allAvailableDaysListT = new ArrayList<>(){{ add(1); add(2); add(3); add(4); add(5); add(6); add(7); add(31);}};
        Category category = new Category()
                .setName(CategoryNameEnum.VINTAGE)
                .setDescription("Manufactured between 1919 and 1930.");
        categoryRepository.save(category);

        CarServiceModel testCarServiceModel = new CarServiceModel()
                .setAllAvailableDays(allAvailableDaysListT)
                .setBrand("testBrand")
                .setName("testBrand")
                .setDescription("Very long description for test.")
                .setModel("testModel")
                .setCategory(category)
                .setFile("ImageUrl with beautiful test image.")
                .setCreated(1900);

        carServiceToTest.addNewCar(testCarServiceModel);

        Optional<Car> car = carRepository.findById((long)1);

        Assertions.assertNotNull(car);
        Assertions.assertTrue(car.isPresent());

    }



}
