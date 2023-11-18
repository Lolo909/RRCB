package com.example.rrcb.service;

import com.example.rrcb.model.entity.Category;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    Category findCategoryByName(CategoryNameEnum categoryNameEnum);
}
