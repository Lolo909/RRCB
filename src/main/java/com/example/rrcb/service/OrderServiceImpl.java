package com.example.rrcb.service;

import com.example.rrcb.model.view.OrderViewModel;
import com.example.rrcb.model.view.UserViewModel;
import com.example.rrcb.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.rrcb.model.entity.enums.RoleNameEnum.ADMIN;
import static com.example.rrcb.model.entity.enums.RoleNameEnum.USER;

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

            return orderViewModel;
        }).collect(Collectors.toList());
    }
}
