package com.example.rrcb.service;

import com.example.rrcb.model.entity.Order;
import com.example.rrcb.model.entity.OrderDay;
import com.example.rrcb.model.view.OrderDayViewModel;
import com.example.rrcb.repository.CarRepository;
import com.example.rrcb.repository.OrderDayRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDayServiceImpl implements OrderDayService{

    private final OrderDayRepository orderDayRepository;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;

    public OrderDayServiceImpl(OrderDayRepository orderDayRepository, ModelMapper modelMapper, CarRepository carRepository) {
        this.orderDayRepository = orderDayRepository;
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
    }


    @Override
    public OrderDayViewModel findOrderDayById(Long id) {
        List<OrderDay> allOrdersByUser = orderDayRepository.findAllByUserId(id);

        return null;
    }
}
