package com.example.rrcb.service;

import com.example.rrcb.model.entity.Category;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategoryByName(CategoryNameEnum categoryNameEnum) {
        return categoryRepository.findByName(categoryNameEnum).orElse(null);
    }
}
