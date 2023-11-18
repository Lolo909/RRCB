package com.example.rrcb.repository;

import com.example.rrcb.model.entity.Category;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(CategoryNameEnum categoryNameEnum);
}
