package ru.practicum.event.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.category.service.CategoryService;
import ru.practicum.error.exception.BadRequestException;
import ru.practicum.error.exception.NotFoundException;
import ru.practicum.error.exception.ValidationException;
import ru.practicum.event.dto.*;
import ru.practicum.event.enums.EventSort;
import ru.practicum.event.enums.EventState;
import ru.practicum.event.mapper.EventMapper;
import ru.practicum.event.model.Event;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.stats.EwmStatsDto;
import ru.practicum.stats.StatsClient;
import ru.practicum.user.model.User;
import ru.practicum.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    StatsClient statsClient;


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
        log.debug("Update user parameters event, SERVICE");
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
        Event event = getEntityById(eventId);
        return EventMapper.toEventDto(event);
    }

    @Transactional
    public EventDto cancelEventById(Integer userId, Integer eventId) {
        log.debug("Cancel event by id, SERVICE");
        Event event = getEntityById(eventId);
        event.setState(EventState.CANCELED);
        eventRepository.save(event);
        log.debug("Event canceled, SERVICE");
        return EventMapper.toEventDto(event);
    }

    @Override
    public EventDto getEventByIdPublic(Integer eventId, HttpServletRequest httpServletRequest) {
        log.debug("Get event by id= {}, SERVICE", eventId);
        saveEndpointHit(httpServletRequest);
        Event event = getEntityById(eventId);
        return EventMapper.toEventDto(event);
    }

    @Override
    public List<EventShortDto> getAllEventsPublic(String text, List<Integer> catIds, Boolean paid, LocalDateTime rangeStart,
                                                  LocalDateTime rangeEnd, Boolean onlyAvailable, String sort, Integer from, Integer size, HttpServletRequest httpServletRequest) {
        saveEndpointHit(httpServletRequest);
        String sorting;
        if (sort.equals(EventSort.EVENT_DATE.toString())) {
            sorting = "eventDate";
        } else if (sort.equals(EventSort.VIEWS.toString())) {
            sorting = "views";
        } else {
            sorting = "id";
        }
        if (Objects.isNull(rangeStart)) {
            rangeStart = LocalDateTime.now();
        }
        if (Objects.isNull(rangeEnd)) {
            rangeEnd = LocalDateTime.now().plusYears(100);
        }
        Pageable pageable = PageRequest.of(from / size, size, Sort.by(sorting));
        log.info("get all filtered events");
        List<Event> sortedEvents = eventRepository.getFilterEvents(text, catIds, paid, rangeStart, rangeEnd, pageable);
        if (onlyAvailable) {
            sortedEvents.removeIf(event -> event.getParticipants().size() == event.getParticipantLimit());
        }
        eventRepository.saveAll(sortedEvents);
        return EventMapper.toEventShortDto(sortedEvents);
    }

    @Transactional
    public EventDto acceptEventById(Integer eventId) {
        log.debug("Accept event by id, SERVICE");
        Event event = getEntityById(eventId);
        event.setState(EventState.PUBLISHED);
        event.setPublishedOn(LocalDateTime.now());
        eventRepository.save(event);
        log.debug("Event accepted, SERVICE");
        return EventMapper.toEventDto(event);
    }

    @Transactional
    public EventDto rejectEventById(Integer eventId) {
        log.debug("Reject event by id, SERVICE");
        Event event = getEntityById(eventId);
        event.setState(EventState.CANCELED);
        eventRepository.save(event);
        log.debug("Event rejected, SERVICE");
        return EventMapper.toEventDto(event);
    }

    @Transactional
    public EventDto updateAdmin(AdminUpdateDto adminUpdateDto, Integer eventId) {
        log.debug("Update admin parameters event, SERVICE");
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
    public List<EventDto> getAllEventsAdmin(List<Integer> users, List<EventState> states, List<Integer> categories,
                                            LocalDateTime rangeStart, LocalDateTime rangeEnd, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        if (rangeStart == null) {
            rangeStart = LocalDateTime.now();
        }
        return eventRepository.getAllUsersEvents(users, categories, states, rangeStart, rangeEnd, pageable)
                .stream()
                .map(EventMapper::toEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public Event getEntityById(Integer id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException("event with id =" + id + " not found"));
    }

    @Override
    public List<Event> getAllEventsByIds(List<Integer> ids) {
        log.debug("Get events by ids, SERVICE");
        return eventRepository.getByIdIn(ids);
    }

    EventDto updateEvent(Event event, LocalDateTime eventDate, Boolean paid, String description,
                         Integer participantLimit, String annotation, String title, Integer category) {
        log.debug("Update parameters event, SERVICE");
        Optional.ofNullable(eventDate).ifPresent(event::setEventDate);
        Optional.ofNullable(paid).ifPresent(event::setPaid);
        Optional.ofNullable(description).ifPresent(event::setDescription);
        Optional.ofNullable(participantLimit).ifPresent(event::setParticipantLimit);
        Optional.ofNullable(annotation).ifPresent(event::setAnnotation);
        Optional.ofNullable(title).ifPresent(event::setTitle);
        Optional.ofNullable(category).ifPresent(c -> event.setCategory(categoryService.getEntityById(c)));
        eventRepository.save(event);
        log.debug("Event updated id - {}, SERVICE", event.getId());
        return EventMapper.toEventDto(event);
    }

    void saveEndpointHit(HttpServletRequest request) {
        EwmStatsDto ewmStatsDto = new EwmStatsDto(
                request.getServerName(),
                request.getRequestURI(),
                request.getRemoteAddr(),
                LocalDateTime.now());
        statsClient.postStats(ewmStatsDto);
    }
}
