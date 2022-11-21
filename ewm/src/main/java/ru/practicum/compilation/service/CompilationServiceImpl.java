package ru.practicum.compilation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.compilation.dto.CompilationDto;
import ru.practicum.compilation.repository.CompilationRepository;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService { //todo

    @Autowired
    CompilationRepository compilationRepository;

    @Autowired
    EventService eventService;

    @Override
    public CompilationDto create(CompilationDto compilationDto) {
        return null;
    }

    @Override
    public void delete(Integer compId) {

    }

    @Override
    public void createPinInCompilation(Integer compId) {

    }

    @Override
    public void deletePinInCompilation(Long compId) {

    }

    @Override
    public void createEventInCompilation(Integer compId, Integer eventId) {

    }

    @Override
    public void deleteEventInCompilation(Integer compId, Integer eventId) {

    }

    @Override
    public CompilationDto getCompilationById(Integer compId) {
        return null;
    }

    @Override
    public List<CompilationDto> getAllCompilationsWithParams(Boolean pinned, Integer from, Integer size) {
        return null;
    }
}
