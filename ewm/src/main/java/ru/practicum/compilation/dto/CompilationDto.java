package ru.practicum.compilation.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.event.dto.EventShortDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class CompilationDto {

    Integer id;

    List<EventShortDto> events;

    @Size(max = 128)
    @NotNull
    String title;

    Boolean pinned;
}