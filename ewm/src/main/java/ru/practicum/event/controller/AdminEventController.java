package ru.practicum.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.event.dto.AdminUpdateDto;
import ru.practicum.event.dto.EventDto;
import ru.practicum.event.enums.EventState;
import ru.practicum.event.service.EventService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class AdminEventController {

    @Autowired
    EventService eventService;

    @PatchMapping("/{eventId}/publish")
    @ResponseStatus(HttpStatus.OK)
    public EventDto acceptEventById(@PathVariable Integer eventId) {
        log.info("PATCH /eventId/publish: {}", eventId);
        return eventService.acceptEventById(eventId);
    }

    @PatchMapping("/{eventId}/reject")
    @ResponseStatus(HttpStatus.OK)
    public EventDto rejectEventById(@PathVariable Integer eventId) {
        log.info("PATCH /eventId/reject: {}", eventId);
        return eventService.rejectEventById(eventId);
    }

    @PutMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventDto updateAdmin(@RequestBody AdminUpdateDto adminUpdateDto, @PathVariable Integer eventId) {
        log.info("PUT /admin/events/eventId: {}, updateEvent - {}", eventId, adminUpdateDto);
        return eventService.updateAdmin(adminUpdateDto, eventId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventDto> getAllEventsAdmin(@RequestParam(required = false) List<Integer> users,
                                            @RequestParam(required = false) List<EventState> states,
                                            @RequestParam(required = false) List<Integer> categories,
                                            @RequestParam(name = "rangeStart", required = false)
                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                            @RequestParam(name = "rangeEnd", required = false)
                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer from,
                                            @Positive @RequestParam(required = false, defaultValue = "10") Integer size) {
        log.info("GET ADMIN all event with params");
        return eventService.getAllEventsAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
    }
}
