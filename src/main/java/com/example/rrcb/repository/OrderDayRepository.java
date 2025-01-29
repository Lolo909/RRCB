package com.example.rrcb.repository;

import com.example.rrcb.model.entity.OrderDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDayRepository extends JpaRepository<OrderDay, Long> {


}
