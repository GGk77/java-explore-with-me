package ru.practicum.category.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class CategoryDto {

    Integer id;

    @Size(max = 30)
    @NotBlank
    @NotNull
    String name;
}