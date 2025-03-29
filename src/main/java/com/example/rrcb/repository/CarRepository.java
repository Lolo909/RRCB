package com.example.rrcb.repository;

import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByCategory_Name(CategoryNameEnum categoryNameEnum);

    Page<Car> findAll(Pageable pageable);

    @Query("SELECT c FROM Car c WHERE " +
            "LOWER(c.name) LIKE %:searchTerm% OR " +
            "LOWER(c.brand) LIKE %:searchTerm% OR " +
            "LOWER(c.model) LIKE %:searchTerm% OR " +
            "CONCAT(c.created, '') LIKE %:searchTerm%")
    Page<Car> searchAllCars(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Car c WHERE " +
            "LOWER(c.name) LIKE %:searchTerm% OR " +
            "LOWER(c.brand) LIKE %:searchTerm% OR " +
            "LOWER(c.model) LIKE %:searchTerm% OR " +
            "CONCAT(c.created, '') LIKE %:searchTerm%")
    long countSearchAllCars(@Param("searchTerm") String searchTerm);


}
