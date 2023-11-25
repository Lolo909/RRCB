package com.example.rrcb.repository;

import com.example.rrcb.model.entity.Order;
import com.example.rrcb.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
