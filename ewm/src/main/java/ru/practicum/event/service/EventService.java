package ru.practicum.event.service;

import ru.practicum.event.dto.*;

import java.util.List;
import java.util.Optional;

public interface EventService {

    EventDto create(NewEventDto newEventDto, Integer userId);

    EventDto update(UpdateEventDto updateEventDto, Integer userId);

    List<EventShortDto> getAllEventsUserById(Integer userId, Integer from, Integer size);

    EventDto getEventById(Integer userId, Integer eventId);

    EventDto cancelEventById(Integer userId, Integer eventId);

    EventDto getEventByIdPublic(Integer eventId);

    List<EventShortDto> getAllEventsPublic(Optional<String> text,
                                           Optional<List<Integer>> categories,
                                           Optional<Boolean> paid, Optional<String> start,
                                           Optional<String> end,
                                           Optional<String> available,
                                           Optional<String> sort,
                                           Integer from,
                                           Integer size);

    EventDto acceptEventById(Integer eventId);

    EventDto rejectEventById(Integer eventId);

    EventDto updateAdmin(AdminUpdateDto adminUpdateDto, Integer eventId);

    List<EventDto> getAllEventsAdmin(Optional<List<Integer>> users,
                                     Optional<List<String>> states,
                                     Optional<List<Integer>> categories,
                                     Optional<String> start,
                                     Optional<String> end,
                                     Integer from,
                                     Integer size);

    Boolean existsByCategoryId(Integer categoryId);

}
