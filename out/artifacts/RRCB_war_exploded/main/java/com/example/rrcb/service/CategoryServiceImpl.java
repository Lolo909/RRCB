package com.example.rrcb.service;

import com.example.rrcb.model.entity.Category;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.repository.CategoryRepository;
import com.example.rrcb.service.exeption.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
//.orElseThrow(()->new ObjectNotFoundException("Car for rent with id "+id+" is not found."));
    @Override
    public Category findCategoryByName(CategoryNameEnum categoryNameEnum) {
        return categoryRepository.findByName(categoryNameEnum).orElseThrow(() -> new ObjectNotFoundException("Category with name "+ categoryNameEnum+" is not found."));
    }
}
