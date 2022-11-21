package ru.practicum.compilation.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
public class NewCompilationDto {

    List<Integer> eventsId;

    Boolean pinned;

    @NotBlank
    String title;
}
