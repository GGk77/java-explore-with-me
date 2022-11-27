package ru.practicum.event.service;

import ru.practicum.event.dto.*;
import ru.practicum.event.enums.EventState;
import ru.practicum.event.model.Event;

import java.util.List;

public interface EventService {

    EventDto create(NewEventDto newEventDto, Integer userId);

    EventDto update(UpdateEventDto updateEventDto, Integer userId);

    List<EventShortDto> getAllEventsUserById(Integer userId, Integer from, Integer size);

    EventDto getEventByUserId(Integer userId, Integer eventId);

    EventDto cancelEventById(Integer userId, Integer eventId);

    EventDto getEventByIdPublic(Integer eventId);

    List<EventShortDto> getAllEventsPublic(String text, List<Integer> categories, Boolean paid,
                                           String rangeStart, String rangeEnd, Boolean onlyAvailable,
                                           String sort, Integer from, Integer size);

    EventDto acceptEventById(Integer eventId);

    EventDto rejectEventById(Integer eventId);

    EventDto updateAdmin(AdminUpdateDto adminUpdateDto, Integer eventId);

    List<EventDto> getAllEventsAdmin(List<Integer> users, List<EventState> states, List<Integer> categories,
                                     String rangeStart, String rangeEnd, Integer from, Integer size);

    Event getEntityById(Integer id);

    List<Event> getAllEventsByIds(List<Integer> ids);

}
