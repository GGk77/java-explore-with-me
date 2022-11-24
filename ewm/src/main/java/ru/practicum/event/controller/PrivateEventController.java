package ru.practicum.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.event.dto.EventDto;
import ru.practicum.event.dto.EventShortDto;
import ru.practicum.event.dto.NewEventDto;
import ru.practicum.event.dto.UpdateEventDto;
import ru.practicum.event.service.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
public class PrivateEventController {

    @Autowired
    EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public EventDto create(@RequestBody @Valid NewEventDto newEventDto, @PathVariable Integer userId) {
        log.info("POST /users/userId/events: {}, userId = {}", newEventDto, userId);
        return eventService.create(newEventDto, userId);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public EventDto update(@RequestBody @Valid UpdateEventDto updateEventDto, @PathVariable Integer userId) {
        log.info("PATCH /users/userId/events: {}, userId = {}", updateEventDto, userId);
        return eventService.update(updateEventDto, userId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventShortDto> getAllEventsUserById(@PathVariable Integer userId,
                                                 @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                 @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("GET PRIVATE all events by userId");
        return eventService.getAllEventsUserById(userId, from, size);
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventDto getEventById(@PathVariable Integer userId, @PathVariable Integer eventId) {
        log.info("GET /users/userId/events/eventId = {}, userId = {}", eventId, userId);
        return eventService.getEventByUserId(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventDto cancelEventById(@PathVariable Integer userId, @PathVariable Integer eventId) {
        log.info("PATCH /users/userId/events/eventId = {}, userId = {}", eventId, userId);
        return eventService.cancelEventById(userId, eventId);
    }
}
