package ru.practicum.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@ToString
@Jacksonized
public class CategoryDto {

    Integer id;
    @NotBlank
    String name;
}