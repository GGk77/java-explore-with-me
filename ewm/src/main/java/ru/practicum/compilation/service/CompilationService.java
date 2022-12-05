package ru.practicum.compilation.service;

import ru.practicum.compilation.dto.CompilationDto;
import ru.practicum.compilation.dto.NewCompilationDto;
import ru.practicum.compilation.model.Compilation;

import java.util.List;

public interface CompilationService {
    CompilationDto create(NewCompilationDto compilationDto);

    void delete(Integer compId);

    void createPinInCompilation(Integer compId);

    void deletePinInCompilation(Integer compId);

    void createEventInCompilation(Integer compId, Integer eventId);

    void deleteEventInCompilation(Integer compId, Integer eventId);

    CompilationDto getCompilationById(Integer compId);

    List<CompilationDto> getAllCompilationsWithParams(Boolean pinned, Integer from, Integer size);

    Compilation getEntityById(Integer compId);
}
