package ru.practicum.category.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.service.CategoryService;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto create(@RequestBody @Valid CategoryDto categoryDto) {
        log.info("POST /admin/categories: {}", categoryDto);
        return categoryService.create(categoryDto);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto update(@RequestBody @Valid CategoryDto categoryDto) {
        log.info("PATCH /admin/categories: {}", categoryDto);
        return categoryService.update(categoryDto);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @NonNull Integer catId) {
        log.info("DELETE /admin/categories/categoryId: {}", catId);
        categoryService.delete(catId);
    }
}
