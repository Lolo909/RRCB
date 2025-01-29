package com.example.rrcb.service;

import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.Order;
import com.example.rrcb.model.view.OrderViewModel;
import com.example.rrcb.repository.CarRepository;
import com.example.rrcb.repository.OrderRepository;
import com.example.rrcb.service.exeption.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, CarRepository carRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
    }


    @Override
    public List<OrderViewModel> findAllRentsOfTheUserByName(String name) {

        return orderRepository.findAllByUser_Username(name).stream().map(order -> {
            OrderViewModel orderViewModel = modelMapper.map(order, OrderViewModel.class);
            DateTimeFormatter pattern = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
            orderViewModel.setDateTime(order.getDateTime().format(pattern));
            return orderViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public List<OrderViewModel> findAllRents() {
        return orderRepository.findAll().stream().map(order -> {
            OrderViewModel orderViewModel = modelMapper.map(order, OrderViewModel.class);
            DateTimeFormatter pattern = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
            orderViewModel.setDateTime(order.getDateTime().format(pattern));
            return orderViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public void ClearUp() {
        orderRepository.deleteAll();
    }

    @Override
    public void clearAllOrdersFromUserByUserId(Long id) {
        orderRepository.deleteAllByUserId(id);
    }

    @Override
    public void restoreCarAvailableDaysByUserId(Long id) {
        /*
        List<Order> allOrdersByUser = orderRepository.findAllByUserId(id);
        for (var order: allOrdersByUser) {
            List<Integer> daysOrdered = order.getAllOrderedDays();
            Car car = carRepository.findById(order.getCar().getId()).orElseThrow(() -> new ObjectNotFoundException("Car is not found"));

            Integer[] listWithOrderedDays = car.getAllOrderDays().toArray(new Integer[0]);
            List<Integer> newListWithOrderedDays =  car.getAllOrderDays();
            List<Integer> newListWithAllAvailableDays =  car.getAllAvailableDays();

            for (var carDayOrdered: listWithOrderedDays) {
                if(daysOrdered.contains(carDayOrdered)){
                    newListWithOrderedDays.remove(carDayOrdered);
                    newListWithAllAvailableDays.add(carDayOrdered);
                }
            }

            Collections.sort(newListWithAllAvailableDays);

            car.setAllOrderDays(newListWithOrderedDays);
            car.setAllAvailableDays(newListWithAllAvailableDays);

            carRepository.saveAndFlush(car);
        }
        */
    }
}
