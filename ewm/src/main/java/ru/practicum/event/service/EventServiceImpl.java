package ru.practicum.event.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.category.mapper.CategoryMapper;
import ru.practicum.category.service.CategoryService;
import ru.practicum.error.exception.BadRequestException;
import ru.practicum.error.exception.ValidationException;
import ru.practicum.event.dto.*;
import ru.practicum.event.enums.EventState;
import ru.practicum.event.mapper.EventMapper;
import ru.practicum.event.model.Event;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.error.exception.NotFoundException;
import ru.practicum.user.model.User;
import ru.practicum.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Transactional
    public EventDto create(NewEventDto newEventDto, Integer userId) {
        log.debug("Create event, SERVICE");
        if (LocalDateTime.now().plusHours(2).isAfter(newEventDto.getEventDate())) {
            throw new ValidationException("bad data");
        }
        Event event = EventMapper.toEvent(newEventDto);
        User user = userService.getEntityById(userId);
        event.setInitiator(user);
        event.setCategory(CategoryMapper.toCategory(categoryService.getCategoryById(newEventDto.getCategory())));
        eventRepository.save(event);
        log.debug("Event with id = {}, created", event.getId());
        return EventMapper.toEventDto(event);
    }

    @Transactional
    public EventDto update(UpdateEventDto updateEventDto, Integer userId) {
        if (LocalDateTime.now().plusHours(2).isAfter(updateEventDto.getEventDate())) {
            throw new ValidationException("bad data");
        }
        Event event = getEntityById(updateEventDto.getEventId());
        User user = userService.getEntityById(userId);
        if (!event.getInitiator().getId().equals(user.getId())) {
            throw new BadRequestException("the event belongs to another user");
        }
        if (!event.getState().equals(EventState.PENDING)) {
            throw new BadRequestException("this request is not in the pending state");
        }
        return null; //todo
    }

    @Override
    public List<EventShortDto> getAllEventsUserById(Integer userId, Integer from, Integer size) {
        log.debug("Get all event by user id= {}  | SERVICE", userId);
        Pageable pageable = PageRequest.of(from / size, size);
        userService.getEntityById(userId);
        List<Event> eventList = eventRepository.getByInitiator_IdOrderByStartDateDesc(userId, pageable);
        return EventMapper.toEventShortDto(eventList);
    }

    @Override
    public EventDto getEventByUserId(Integer userId, Integer eventId) {
        log.debug("Get event by user id= {}, event id= {}  | SERVICE", userId, eventId);
        User user = userService.getEntityById(userId); //??
        Event event = getEntityById(eventId);
        return EventMapper.toEventDto(event);
    }

    @Transactional
    public EventDto cancelEventById(Integer userId, Integer eventId) {
        User user = userService.getEntityById(userId); // ??
        Event event = getEntityById(eventId);
        event.setState(EventState.CANCELED);
        eventRepository.save(event);
        return EventMapper.toEventDto(event);
    }

    @Override
    public EventDto getEventByIdPublic(Integer eventId) {
        log.debug("Get event by id= {}, SERVICE", eventId);
        Event event = getEntityById(eventId);
        return EventMapper.toEventDto(event);
    }

    @Override
    public List<EventShortDto> getAllEventsPublic(Optional<String> text, Optional<List<Integer>> categories, Optional<Boolean> paid, Optional<String> start, Optional<String> end, Optional<String> available, Optional<String> sort, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventDto acceptEventById(Integer eventId) {
        Event event = getEntityById(eventId);
        event.setState(EventState.PUBLISHED);
        event.setPublished(LocalDateTime.now());
        eventRepository.save(event);
        return EventMapper.toEventDto(event);
    }

    @Override
    public EventDto rejectEventById(Integer eventId) {
        Event event = getEntityById(eventId);
        event.setState(EventState.CANCELED);
        eventRepository.save(event);
        return EventMapper.toEventDto(event);
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
    public Event getEntityById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("event with id =" + id + " not found"));
    }

    @Override
    public List<Event> getAllEventsByIds(List<Integer> ids) {
        log.debug("Get events by ids, SERVICE");
        return eventRepository.getEventsByIdIn(ids);
    }
}
