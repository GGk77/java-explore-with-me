package ru.practicum.category.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.mapper.CategoryMapper;
import ru.practicum.category.model.Category;
import ru.practicum.category.repository.CategoryRepository;
import ru.practicum.event.service.EventService;
import ru.practicum.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    @Lazy
    EventService eventService;

    @Transactional
    public CategoryDto create(CategoryDto categoryDto) {
        log.debug("Create category, SERVICE");
        Category category = categoryRepository.save(CategoryMapper.toCategory(categoryDto));
        log.debug("Category with id = {}, created", category.getId());
        return CategoryMapper.toCategoryDto(category);
    }

    @Transactional
    public CategoryDto update(CategoryDto categoryDto) {
        log.debug("Update category, SERVICE");
        Category category = CategoryMapper.toCategory(
                getCategoryById(categoryDto.getId()));
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        log.debug("Category with id= {}, updated", category.getId());
        return CategoryMapper.toCategoryDto(category);
    }

    @Transactional
    public void delete(Integer categoryId) {
        log.debug("Delete category with id= {}, SERVICE", categoryId);
        if (!eventService.existsByCategoryId(categoryId)) {
            categoryRepository.delete(categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new NotFoundException("category with id =" + categoryId + " not found")));
        }
    }

    @Override
    public CategoryDto getCategoryById(Integer catId) {
        log.debug("Get category by id= {}, SERVICE", catId);
        return CategoryMapper.toCategoryDto(categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException("category with id =" + catId + " not found")));
    }

    @Override
    public List<CategoryDto> getAllCategories(Integer from, Integer size) {
        log.debug("Get all categories, SERVICE");
        Pageable pageable = PageRequest.of(from / size, size);
        return categoryRepository.findAll(pageable)
                .stream()
                .map(CategoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }
}
