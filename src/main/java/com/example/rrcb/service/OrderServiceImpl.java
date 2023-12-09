package com.example.rrcb.service;

import com.example.rrcb.model.entity.Order;
import com.example.rrcb.model.view.OrderViewModel;
import com.example.rrcb.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
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
}
