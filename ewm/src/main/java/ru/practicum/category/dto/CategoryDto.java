package ru.practicum.category.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.utils.Create;
import ru.practicum.utils.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CategoryDto {

    @NotNull(groups = {Update.class})
    Integer id;
    @NotBlank(groups = {Update.class, Create.class})
    String name;
}