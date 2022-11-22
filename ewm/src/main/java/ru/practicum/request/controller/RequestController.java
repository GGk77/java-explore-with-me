package ru.practicum.request.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.request.dto.RequestDto;
import ru.practicum.request.service.RequestService;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/users/{userId}")
@RequiredArgsConstructor
public class RequestController {

    @Autowired
    RequestService requestService;

    @PostMapping("/requests")
    @ResponseStatus(HttpStatus.OK)
    public RequestDto create(@PathVariable Integer userId, @RequestParam Optional<Integer> eventId) {
        log.info("POST /users/userId = {}/requests: eventId = {}", userId, eventId);
    return requestService.create(userId, eventId);
    }

    @PatchMapping("/events/{eventId}/requests/{reqId}/confirm")
    @ResponseStatus(HttpStatus.OK)
    public RequestDto confirmRequestParticipation(@PathVariable Integer userId, @PathVariable Integer eventId, @PathVariable Integer reqId) {
        log.info("PATCH /events/eventId= {}/requests/reqId= {}/confirm - userId = {}", eventId, reqId, userId);
        return  requestService.confirmRequestParticipation(userId, eventId, reqId);
    }
}
