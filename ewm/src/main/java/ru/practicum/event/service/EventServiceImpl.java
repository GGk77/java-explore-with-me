package ru.practicum.event.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.event.dto.*;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {


    @Override
    public EventDto create(NewEventDto newEventDto, Integer userId) {
        return null;
    }

    @Override
    public EventDto update(UpdateEventDto updateEventDto, Integer userId) {
        return null;
    }

    @Override
    public List<EventShortDto> getAllEventsUserById(Integer userId, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventDto getEventById(Integer userId, Integer eventId) {
        return null;
    }

    @Override
    public EventDto cancelEventById(Integer userId, Integer eventId) {
        return null;
    }

    @Override
    public EventDto getEventByIdPublic(Integer eventId) {
        return null;
    }

    @Override
    public List<EventShortDto> getAllEventsPublic(Optional<String> text, Optional<List<Integer>> categories, Optional<Boolean> paid, Optional<String> start, Optional<String> end, Optional<String> available, Optional<String> sort, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventDto acceptEventById(Integer eventId) {
        return null;
    }

    @Override
    public EventDto rejectEventById(Integer eventId) {
        return null;
    }

    @Override
    public EventDto updateAdmin(AdminUpdateDto adminUpdateDto, Integer eventId) {
        return null;
    }

    @Override
    public List<EventDto> getAllEventsAdmin(Optional<List<Integer>> users, Optional<List<String>> states, Optional<List<Integer>> categories, Optional<String> start, Optional<String> end, Integer from, Integer size) {
        return null;
    }

    @Override
    public Boolean existsByCategoryId(Integer categoryId) {
        return null;
    }
}
