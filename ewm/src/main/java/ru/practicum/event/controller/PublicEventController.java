package ru.practicum.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.event.dto.EventDto;
import ru.practicum.event.dto.EventShortDto;
import ru.practicum.event.service.EventService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/events")
@RequiredArgsConstructor
public class PublicEventController {

    @Autowired
    EventService eventService;

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventDto getEventByIdPublic(@PathVariable Integer eventId) {
        log.info("GET events/eventId = {}", eventId);
        return eventService.getEventByIdPublic(eventId);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventShortDto> getAllEventsPublic(@RequestParam Optional<String> text,
                                                  @RequestParam Optional<List<Integer>> categories,
                                                  @RequestParam Optional<Boolean> paid,
                                                  @RequestParam Optional<String> start,
                                                  @RequestParam Optional<String> end,
                                                  @RequestParam Optional<String> available,
                                                  @RequestParam Optional<String> sort,
                                                  @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                  @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("GET PUBLIC all event with params");
        return eventService.getAllEventsPublic(text, categories, paid, start, end, available, sort, from, size);
    }
}
