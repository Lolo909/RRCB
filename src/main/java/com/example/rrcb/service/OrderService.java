package com.example.rrcb.service;
import com.example.rrcb.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    List<OrderViewModel> findAllRentsOfTheUserByName(String name);

    List<OrderViewModel> findAllRents();

    void ClearUp();

    void clearAllOrdersFromUserByUserId(Long id);

    void restoreCarAvailableDaysByUserId(Long id);

    void removeOrderWithId(Long id);

    void removeOrderWithIdWithinXDaysAway(Long id, int daysAway);

    void removeAllOrdersWithCarId(Long id);


}
