package com.example.rrcb.service;

import com.example.rrcb.model.view.CarDetailsViewModel;
import com.example.rrcb.model.view.OrderDayViewModel;

public interface OrderDayService {

    OrderDayViewModel findOrderDayById(Long id);
}
