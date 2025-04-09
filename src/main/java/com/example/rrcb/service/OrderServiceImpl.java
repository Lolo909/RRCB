package com.example.rrcb.service;

import com.example.rrcb.model.entity.Order;
import com.example.rrcb.model.entity.OrderDay;
import com.example.rrcb.model.view.OrderViewModel;
import com.example.rrcb.repository.CarRepository;
import com.example.rrcb.repository.OrderDayRepository;
import com.example.rrcb.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private final OrderDayRepository orderDayRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, CarRepository carRepository, OrderDayRepository orderDayRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
        this.orderDayRepository = orderDayRepository;
    }


    @Override
    public List<OrderViewModel> findAllRentsOfTheUserByName(String name) {

        return orderRepository.findAllByUser_Username(name).stream().map(order -> {
            OrderViewModel orderViewModel = modelMapper.map(order, OrderViewModel.class);
            DateTimeFormatter pattern = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.ENGLISH);
            orderViewModel.setDateTime(order.getDateTime().format(pattern));
            return orderViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public List<OrderViewModel> findAllRents() {
        return orderRepository.findAll().stream().map(order -> {
            OrderViewModel orderViewModel = modelMapper.map(order, OrderViewModel.class);
            DateTimeFormatter pattern = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.ENGLISH);
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
        List<Order> allOrders = orderRepository.findAllByUserId(id);
        allOrders.stream()
                .forEach(order -> {
                    removeOrderWithId(order.getId());
                    orderRepository.flush();
                });
        orderRepository.flush();
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

    @Override
    public void removeOrderWithId(Long id) {
        List<OrderDay> allOrderDays = orderDayRepository.findAll();

        // Step 1: Detach the order reference
        allOrderDays.stream()
                .filter(orderDay -> orderDay.getOrder() != null && orderDay.getOrder().getId().equals(id))
                .forEach(orderDay -> {
                    orderDay.setOrder(null);
                    orderDayRepository.save(orderDay);
                });

        orderDayRepository.flush(); // Flushing the correct repository

        // Step 2: Delete OrderDay entities with null orders
        List<OrderDay> orderDaysToDelete = allOrderDays.stream()
                .filter(orderDay -> orderDay.getOrder() == null)
                .toList();

        orderDayRepository.deleteAll(orderDaysToDelete);
        orderDayRepository.flush();


        List<Order> allOrders = orderRepository.findAll();
        allOrders.stream()
                .filter(order -> order.getUser() != null && order.getCar() != null && order.getId().equals(id))
                .forEach(order -> {
                    order.setUser(null);
                    order.setCar(null);
                    orderRepository.save(order);
                });

        List<Order> ordersToDelete = allOrders.stream()
                .filter(order -> order.getUser() == null && order.getCar() == null)
                .toList();

        orderRepository.deleteAll(ordersToDelete);
        orderRepository.flush();

        // Step 3: Delete the Order itself
//        orderRepository.deleteById(id);
//        System.out.println(id);
//        orderRepository.flush();
    }

    @Override
    public void removeAllOrdersWithCarId(Long id) {
        List<Order> allOrders = orderRepository.findAllByCar_Id(id);
        allOrders.stream()
                .forEach(order -> {
                    removeOrderWithId(order.getId());
                });
        orderRepository.flush();
    }


}
