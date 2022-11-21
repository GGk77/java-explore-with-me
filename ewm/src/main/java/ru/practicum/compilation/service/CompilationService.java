package ru.practicum.compilation.service;

import ru.practicum.compilation.dto.CompilationDto;

import java.util.List;

public interface CompilationService {
    CompilationDto create(CompilationDto compilationDto);

    void delete(Integer compId);

    void createPinInCompilation(Integer compId);

    void deletePinInCompilation(Long compId);

    void createEventInCompilation(Integer compId, Integer eventId);

    void deleteEventInCompilation(Integer compId, Integer eventId);

    CompilationDto getCompilationById(Integer compId);

    List<CompilationDto> getAllCompilationsWithParams(Boolean pinned, Integer from, Integer size);
}
