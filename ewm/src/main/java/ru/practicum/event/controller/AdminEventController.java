package ru.practicum.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.event.dto.AdminUpdateDto;
import ru.practicum.event.dto.EventDto;
import ru.practicum.event.model.Event;
import ru.practicum.event.service.EventService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Optional;

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
    public List<EventDto> getAllEventsAdmin(@RequestParam Optional<List<Integer>> users,
                                            @RequestParam Optional<List<String>> states,
                                            @RequestParam Optional<List<Integer>> categories,
                                            @RequestParam Optional<String> start,
                                            @RequestParam Optional<String> end,
                                            @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                            @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("GET ADMIN all event with params");
        return eventService.getAllEventsAdmin(users, states, categories, start, end, from, size);
    }
}
