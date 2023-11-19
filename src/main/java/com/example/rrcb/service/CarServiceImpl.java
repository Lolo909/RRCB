package com.example.rrcb.service;

import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.model.service.CarServiceModel;
import com.example.rrcb.model.view.CarViewModel;
import com.example.rrcb.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void addNewCar(CarServiceModel carServiceModel) {
        Car car = modelMapper.map(carServiceModel, Car.class);
//        car.setCategories(carServiceModel.getCategories().stream().map(
//                categoryNameEnum ->
//                        categoryService.findCategoryByName(categoryNameEnum.getName())
//        ).collect(Collectors.toSet()));
        //TODO set current user

        carRepository.save(car);
        /*Route route = modelMapper.map(routeServiceModel, Route.class);

        //route.setAuthor(userService.findCurrentLoginUserEntity());
        route.setCategories(routeServiceModel.getCategories().stream().map(
                categoryNameEnum ->
                    categoryService.findCategoryByName(categoryNameEnum)

        ).collect(Collectors.toSet()));

        routeRepository.save(route);*/
    }

    @Override
    public List<CarViewModel> findAllCarsView() {
        return carRepository.findAll().stream().map(car -> {
            CarViewModel carViewModel = modelMapper.map(car, CarViewModel.class);

//            if (car.getPictures().isEmpty()){
//                carViewModel.setPictureUrl("/images/pic4.jpg");
//            }else{
                carViewModel.setImageUrl((car.getImages().stream().findFirst().get().getUrl()));
//            }
            return carViewModel;
        }).collect(Collectors.toList());
    }

    public List<CarViewModel> findAllCarsViewByCategory(CategoryNameEnum categoryNameEnum) {
        return carRepository.findAllByCategory_Name(categoryNameEnum).stream().map(car -> {
            CarViewModel carViewModel = modelMapper.map(car, CarViewModel.class);

//            if (car.getPictures().isEmpty()){
//                carViewModel.setPictureUrl("/images/pic4.jpg");
//            }else{
            carViewModel.setImageUrl((car.getImages().stream().findFirst().get().getUrl()));
//            }
            return carViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllUrlS() {
        return carRepository.findAll().stream().map(car -> {
            CarViewModel carViewModel = modelMapper.map(car, CarViewModel.class);
            carViewModel.setImageUrl((car.getImages().stream().findFirst().get().getUrl()));
            return carViewModel.getImageUrl();
        }).collect(Collectors.toList());
    }
}
