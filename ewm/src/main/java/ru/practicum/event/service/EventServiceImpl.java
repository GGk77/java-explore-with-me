package ru.practicum.event.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.category.service.CategoryService;
import ru.practicum.error.exception.BadRequestException;
import ru.practicum.error.exception.NotFoundException;
import ru.practicum.error.exception.ValidationException;
import ru.practicum.event.dto.*;
import ru.practicum.event.enums.EventState;
import ru.practicum.event.mapper.EventMapper;
import ru.practicum.event.model.Event;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.user.model.User;
import ru.practicum.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
//todo
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
        event.setCategory(categoryService.getEntityById(newEventDto.getCategory()));
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
        event.setState(EventState.PENDING);
        return updateEvent(event, updateEventDto.getEventDate(), updateEventDto.getPaid(), updateEventDto.getDescription(),
                updateEventDto.getParticipantLimit(), updateEventDto.getAnnotation(), updateEventDto.getTitle(),
                updateEventDto.getCategory());
    }

    @Override
    public List<EventShortDto> getAllEventsUserById(Integer userId, Integer from, Integer size) {
        log.debug("Get all event by user id= {}  | SERVICE", userId);
        Pageable pageable = PageRequest.of(from / size, size);
        userService.getEntityById(userId);
        List<Event> eventList = eventRepository.getByInitiator_IdOrderByEventDateDesc(userId, pageable);
        return EventMapper.toEventShortDto(eventList);
    }

    @Override
    public EventDto getEventByUserId(Integer userId, Integer eventId) {
        log.debug("Get event by user id= {}, event id= {}  | SERVICE", userId, eventId);
        User user = userService.getEntityById(userId); // ?? todo нужен ли?
        Event event = getEntityById(eventId);
        return EventMapper.toEventDto(event);
    }

    @Transactional
    public EventDto cancelEventById(Integer userId, Integer eventId) {
        User user = userService.getEntityById(userId); // ?? todo нужен ли?
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
        //todo
    }

    @Override
    public EventDto acceptEventById(Integer eventId) {
        Event event = getEntityById(eventId);
        event.setState(EventState.PUBLISHED);
        event.setPublishedOn(LocalDateTime.now());
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

    @Transactional
    public EventDto updateAdmin(AdminUpdateDto adminUpdateDto, Integer eventId) {
        Event event = getEntityById(eventId);
        Optional.ofNullable(adminUpdateDto.getRequestModeration()).ifPresent(event::setModeration);
        Optional.ofNullable(adminUpdateDto.getRequestModeration()).ifPresent(location -> {
            event.setLat(adminUpdateDto.getLocation().getLat());
            event.setLon(adminUpdateDto.getLocation().getLon());
        });
        return updateEvent(event, adminUpdateDto.getEventDate(), adminUpdateDto.getPaid(), adminUpdateDto.getDescription(),
                adminUpdateDto.getParticipantLimit(), adminUpdateDto.getAnnotation(), adminUpdateDto.getTitle(),
                adminUpdateDto.getCategory());
    }

    @Override
    public List<EventDto> getAllEventsAdmin(Optional<List<Integer>> users, Optional<List<String>> states, Optional<List<Integer>> categories, Optional<String> start, Optional<String> end, Integer from, Integer size) {
        return null;
        //todo
    }

    @Override
    public Event getEntityById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("event with id =" + id + " not found"));
    }

    @Override
    public List<Event> getAllEventsByIds(List<Integer> ids) {
        log.debug("Get events by ids, SERVICE");
        return eventRepository.getByIdIn(ids);
    }

    private EventDto updateEvent(Event event, LocalDateTime eventDate, Boolean paid, String description,
                                 Integer participantLimit, String annotation, String title, Integer category) {
        //todo
    }
}
