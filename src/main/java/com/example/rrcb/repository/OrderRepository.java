package com.example.rrcb.repository;

import com.example.rrcb.model.entity.Order;
import com.example.rrcb.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findAllByUser_Username(String username);
}
