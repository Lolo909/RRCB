package com.example.rrcb.repository;

import com.example.rrcb.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    //Optional<Order> findAllByUser_Username(String username);
    @Query("SELECT o FROM Order o WHERE o.user.username = :name")
    List<Order> findAllByUsername(@Param("name") String name);

    List<Order> findAllByUser_Username(String username);

    void deleteAllByUserId(Long id);

    List<Order> findAllByUserId(Long id);

    List<Order> findAllByCar_Id(Long id);

}
