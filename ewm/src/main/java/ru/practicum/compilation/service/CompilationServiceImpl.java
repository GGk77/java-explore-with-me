package ru.practicum.compilation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.compilation.dto.CompilationDto;
import ru.practicum.compilation.dto.NewCompilationDto;
import ru.practicum.compilation.mapper.CompilationMapper;
import ru.practicum.compilation.model.Compilation;
import ru.practicum.compilation.repository.CompilationRepository;
import ru.practicum.error.exception.NotFoundException;
import ru.practicum.event.model.Event;
import ru.practicum.event.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    @Autowired
    CompilationRepository compilationRepository;

    @Autowired
    EventService eventService;

    @Transactional
    public CompilationDto create(NewCompilationDto compilationDto) {
        log.trace("Create compilation, SERVICE dto: {}", compilationDto.toString());
        List<Event> eventList = eventService.getAllEventsByIds(compilationDto.getEvents());
        Compilation compilation = compilationRepository.save(CompilationMapper.toCompilation(compilationDto, eventList));
        log.trace("Compilation with id = {}, created", compilation.getId());
        return CompilationMapper.toCompilationDto(compilation);
    }

    @Transactional
    public void delete(Integer compId) {
        log.debug("Delete category with id= {}, SERVICE", compId);
        Compilation compilation = getEntityById(compId);
        compilationRepository.delete(compilation);
        log.debug("Compilation with id = {}, deleted", compilation.getId());
    }

    @Transactional
    public void createPinInCompilation(Integer compId) {
        log.debug("Create pin in compilation with id= {}, SERVICE", compId);
        Compilation compilation = getEntityById(compId);
        compilation.setPinned(true);
        compilationRepository.save(compilation);
        log.debug("Compilation with id = {}, pinned", compId);
    }

    @Transactional
    public void deletePinInCompilation(Integer compId) {
        log.debug("Delete pin in compilation with id= {}, SERVICE", compId);
        Compilation compilation = getEntityById(compId);
        compilation.setPinned(false);
        compilationRepository.save(compilation);
        log.debug("Compilation with id = {}, unpinned", compId);
    }

    @Transactional
    public void createEventInCompilation(Integer compId, Integer eventId) {
        log.debug("Create event with id= {} in compilation with id= {}, SERVICE", eventId, compId);
        Event event = eventService.getEntityById(eventId);
        Compilation compilation = getEntityById(compId);
        List<Event> eventList = compilation.getEvents();
        eventList.add(event);
        compilation.setEvents(eventList);
        compilationRepository.save(compilation);
        log.debug("In compilation with id= {}, created event with id= {}", compilation.getId(), event.getId());
    }

    @Transactional
    public void deleteEventInCompilation(Integer compId, Integer eventId) {
        log.debug("Delete event with id= {} in compilation with id= {}, SERVICE", eventId, compId);
        Event event = eventService.getEntityById(eventId);
        Compilation compilation = getEntityById(compId);
        List<Event> eventList = compilation.getEvents();
        eventList.remove(event);
        compilation.setEvents(eventList);
        compilationRepository.save(compilation);
        log.debug("In compilation with id= {}, deleted event with id= {}", compilation.getId(), event.getId());
    }

    @Override
    public CompilationDto getCompilationById(Integer compId) {
        log.debug("Get compilation by id= {}, SERVICE", compId);
        return CompilationMapper.toCompilationDto(compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Compilation with id =" + compId + " not found")));
    }

    @Override
    public List<CompilationDto> getAllCompilationsWithParams(Boolean pinned, Integer from, Integer size) {
        log.debug("Get all compilations, SERVICE");
        Pageable pageable = PageRequest.of(from / size, size);
        if (pinned == null) {
            return compilationRepository.findAll(pageable)
                    .stream()
                    .map(CompilationMapper::toCompilationDto)
                    .collect(Collectors.toList());
        } else return compilationRepository.getByPinnedOrderByPinnedAsc(pinned, pageable)
                .stream()
                .map(CompilationMapper::toCompilationDto)
                .collect(Collectors.toList());
    }

    @Override
    public Compilation getEntityById(Integer compId) {
        return compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Compilation with id =" + compId + " not found"));
    }
}
