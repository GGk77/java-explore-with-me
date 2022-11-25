package ru.practicum.category.service;

import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.model.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto);

    void delete(Integer categoryId);

    CategoryDto getCategoryById(Integer catId);

    List<CategoryDto> getAllCategories(Integer from, Integer size);

    Category getEntityById(Integer id);
}
