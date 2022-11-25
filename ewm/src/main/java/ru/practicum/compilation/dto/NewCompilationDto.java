package ru.practicum.compilation.dto;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Jacksonized
public class NewCompilationDto {

    List<Integer> events;

    Boolean pinned;

    @NotBlank
    String title;
}
