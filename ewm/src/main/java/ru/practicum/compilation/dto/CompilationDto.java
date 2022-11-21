package ru.practicum.compilation.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class CompilationDto {

    Integer id;

    @Size(max = 128)
    @NotNull
    String title;

    Boolean pinned;
}