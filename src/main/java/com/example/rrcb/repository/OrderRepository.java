package com.example.rrcb.repository;

import com.example.rrcb.model.entity.Order;
import com.example.rrcb.model.entity.User;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    //Optional<Order> findAllByUser_Username(String username);
    @Query("SELECT o FROM Order o WHERE o.user.username = :name")
    List<Order> customQuery(@Param("name") String name);

    List<Order> findAllByUser_Username(String username);
}
