package com.example.rrcb.service;

import com.example.rrcb.model.binding.CarEditBindingModel;
import com.example.rrcb.model.binding.OrderAddBindingModel;
import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.Order;
import com.example.rrcb.model.entity.User;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.model.service.CarServiceModel;
import com.example.rrcb.model.view.CarDetailsViewModel;
import com.example.rrcb.model.view.CarRentViewModel;
import com.example.rrcb.model.view.CarViewModel;
import com.example.rrcb.repository.CarRepository;
import com.example.rrcb.repository.OrderRepository;
import com.example.rrcb.service.exeption.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;

    private final static double priceMultiplayer = 100.00;

    public CarServiceImpl(CarRepository carRepository, OrderRepository orderRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService) {
        this.carRepository = carRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }
    @Override
    public void addNewCar(CarServiceModel carServiceModel) {
        Car car = modelMapper.map(carServiceModel, Car.class);

        //TODO set current user

        List<Integer> allAvailableDays = new ArrayList<>();

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);
        for (int i = 1; i <= numberOfDaysInMonth; i++) {
        allAvailableDays.add(i);
        }

        car.setAllAvailableDays(allAvailableDays);

        carRepository.save(car);
    }

    @Override
    public int getNumberOfDaysInMonth(int year,int month)
    {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        return daysInMonth;
    }

    @Override
    public boolean isThereNOTDataAboutAllAvailableDaysInDataBase() {

        List<Boolean> listForCheck= carRepository.findAll().stream().map(car -> {
            return car.getAllAvailableDays().isEmpty();

        }).collect(Collectors.toList());

        return listForCheck.contains(true);
    }

    @Override
    public List<CarDetailsViewModel> findAllCarsView() {
        return carRepository.findAll().stream().map(car -> {
            CarDetailsViewModel carDetailsViewModel = modelMapper.map(car, CarDetailsViewModel.class);
                carDetailsViewModel.setCategory(car.getCategory().getName());
//            if (car.getPictures().isEmpty()){
//                carViewModel.setPictureUrl("/images/pic4.jpg");
//            }else{
                //carViewModel.setImageUrl((car.getImages().stream().findFirst().get().getUrl()));
//            }
            return carDetailsViewModel;
        }).collect(Collectors.toList());
    }

    public List<CarViewModel> findAllCarsViewByCategory(CategoryNameEnum categoryNameEnum) {
        return carRepository.findAllByCategory_Name(categoryNameEnum).stream().map(car -> {
            CarViewModel carViewModel = modelMapper.map(car, CarViewModel.class);

//            if (car.getPictures().isEmpty()){
//                carViewModel.setPictureUrl("/images/pic4.jpg");
//            }else{
            //carViewModel.setImageUrl((car.getImages().stream().findFirst().get().getUrl()));
//            }
            return carViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllUrlS() {
        return carRepository.findAll().stream().map(car -> {
            CarDetailsViewModel carDetailsViewModel = modelMapper.map(car, CarDetailsViewModel.class);
            carDetailsViewModel.setCategory(car.getCategory().getName());
            return carDetailsViewModel.getImageUrl();
        }).collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {
        carRepository.deleteById(id);
    }

    //@CachePut("newest car image")
    @Override
    public String findNewestCarImageUrl() {

        List<Long> listWithIds = carRepository.findAll().stream().map(car -> {
            CarViewModel carViewModel = modelMapper.map(car, CarViewModel.class);

            //carViewModel.setImageUrl((car.getImages().stream().findFirst().get().getUrl()));
            return carViewModel.getId();
        }).toList();

        Long result = getMax(listWithIds);
        Optional<Car> car = carRepository.findById(result);
        return car.get().getImageUrl();
    }

    public Long getMax(List<Long> list){
        Long max = Long.MIN_VALUE;
        for(int i=0; i<list.size(); i++){
            if(list.get(i) > max){
                max = list.get(i);
            }
        }
        return max;
    }

    @Override
    public CarDetailsViewModel findCarById(Long id) {
        return carRepository.findById(id).map(car ->{
                    CarDetailsViewModel carDetailsViewModel = modelMapper.map(car, CarDetailsViewModel.class);
                    carDetailsViewModel.setCategory(car.getCategory().getName());
                    return carDetailsViewModel;
        })
                .orElseThrow(()->new ObjectNotFoundException("Car with id "+id+" is not found."));
    }

    @Override
    public void editCar(Long id, CarEditBindingModel carEditBindingModel) {
        Car carForEdit = carRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Car with is "+id+ " is not found."));

        carForEdit.setName(carEditBindingModel.getName())
                .setBrand(carEditBindingModel.getBrand())
                .setModel(carEditBindingModel.getModel())
                .setDescription(carEditBindingModel.getDescription())
                .setImageUrl(carEditBindingModel.getImageUrl())
                .setCreated(carEditBindingModel.getCreated())
                .setCategory(carEditBindingModel.getCategory());

        carRepository.saveAndFlush(carForEdit);

    }

    @Override
    public CarRentViewModel findCarForRentById(Long id) {
        return carRepository.findById(id).map(car ->{
                    CarRentViewModel carRentViewModel = modelMapper.map(car, CarRentViewModel.class);
                    carRentViewModel.setCategory(car.getCategory().getName());
                    return carRentViewModel;
                })
                .orElseThrow(()->new ObjectNotFoundException("Car for rent with id "+id+" is not found."));
    }


    @Override
    public void rent(Long id, OrderAddBindingModel orderAddBindingModel, Principal principal) {
        Car carForRent = carRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Car with id "+id+" is not found."));


        carForRent.setAllAvailableDays(getAllAvailableDaysMethod(carForRent.getAllAvailableDays(), orderAddBindingModel.getAllOrderDays()));
        //days.setAllOrderedDays(orderAddBindingModel.getAllOrderedDays());

        List<Integer> allOrderDays =  new ArrayList<>();
        for (int num: carForRent.getAllOrderDays()) {
            allOrderDays.add(num);
        }
        for (int num: orderAddBindingModel.getAllOrderDays()) {
            allOrderDays.add(num);
        }

        carForRent.setAllOrderDays(allOrderDays);

        Order order = new Order();
        User user = userService.findUserByName(principal.getName()).orElseThrow(() ->  new UsernameNotFoundException("User with " + principal.getName()+" is not found."));
        order.setUser(user);
        order.setCar(carRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Car with id "+id+" is not found.")));
        order.setDateTime(LocalDateTime.now());
        order.setAllOrderedDays(orderAddBindingModel.getAllOrderDays());

        BigDecimal price = BigDecimal.valueOf(orderAddBindingModel.getAllOrderDays().size() * priceMultiplayer);
        order.setPrice(price);
        orderRepository.save(order);

        carRepository.saveAndFlush(carForRent);
    }



    @Override
    public List<Integer> getAllAvailableDaysMethod(List<Integer> allAvailableDays, List<Integer> allOrderedDays) {

        List<Integer> listWithAllAvailableDays = new ArrayList<>();

        for (int num: allAvailableDays) {
            if (!allOrderedDays.contains(num)){
                listWithAllAvailableDays.add(num);
            }
        }

        return listWithAllAvailableDays;
    }

    @Override
    public void updateOfCarsAllAvailableDays() {
        List<Integer> allAvailableDays = new ArrayList<>();

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);
        for (int i = 1; i <= numberOfDaysInMonth; i++) {
            allAvailableDays.add(i);
        }

        //Empty list for resetting of the allOrderDays
        List<Integer> allOrderDays = new ArrayList<>();

        List<Car> result = carRepository.findAll().stream().map(car ->{
            Car carForUpdate = car;
            carForUpdate.setAllAvailableDays(allAvailableDays);
            carForUpdate.setAllOrderDays(allOrderDays);
            carRepository.saveAndFlush(carForUpdate);
            return car;
        }).collect(Collectors.toList());

    }


}
