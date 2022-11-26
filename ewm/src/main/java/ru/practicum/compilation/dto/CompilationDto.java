package ru.practicum.compilation.dto;

import lombok.*;
import ru.practicum.event.dto.EventShortDto;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompilationDto {

    Integer id;

    List<EventShortDto> events;

    String title;

    Boolean pinned;
}