package ru.practicum.compilation.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.practicum.compilation.dto.CompilationDto;
import ru.practicum.compilation.dto.NewCompilationDto;
import ru.practicum.compilation.model.Compilation;
import ru.practicum.event.mapper.EventMapper;
import ru.practicum.event.model.Event;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CompilationMapper {

    public static List<CompilationDto> toCompilationDto(Page<Compilation> compilations) {
        return compilations.stream()
                .map(CompilationMapper::toCompilationDto)
                .collect(Collectors.toList());
    }

    public static CompilationDto toCompilationDto(Compilation compilation) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .events(EventMapper.toEventShortDto(compilation.getEvents()))
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }

    public static Compilation toCompilation(NewCompilationDto compilationDto, List<Event> events) {
        return Compilation.builder()
                .pinned(Optional.ofNullable(compilationDto.getPinned()).orElse(false))
                .title(compilationDto.getTitle())
                .events(events)
                .build();
    }

}
