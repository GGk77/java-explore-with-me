package ru.practicum.request.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.request.dto.RequestDto;
import ru.practicum.request.service.RequestService;

import java.util.List;
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

    @PatchMapping("/events/{eventId}/requests/{reqId}/reject")
    @ResponseStatus(HttpStatus.OK)
    public RequestDto rejectRequestParticipation(@PathVariable Integer userId, @PathVariable Integer eventId, @PathVariable Integer reqId) {
        log.info("PATCH /events/eventId= {}/requests/reqId= {}/reject - userId = {}", eventId, reqId, userId);
        return requestService.rejectRequestParticipation(userId, eventId, reqId);
    }

    @GetMapping("/events/{eventId}/requests")
    @ResponseStatus(HttpStatus.OK)
    public List<RequestDto> getAllRequestsByInitiatorId(@PathVariable Integer userId, @PathVariable Integer eventId) {
        log.info("GET /events/eventId = {}/requests  | userId = {}", eventId, userId);
        return requestService.getAllRequestsByInitiatorId(userId, eventId);
    }

    @GetMapping("/requests/{requestId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public RequestDto cancelRequest(@PathVariable Integer userId, @PathVariable Integer requestId) {
        log.info("GET /requests/requestId = {}/cancel  | userId = {}", requestId, userId);
        return requestService.cancelRequest(userId, requestId);
    }

    @GetMapping("/requests")
    @ResponseStatus(HttpStatus.OK)
    public List<RequestDto> getAllByParticipationId(@PathVariable Integer userId) {
        log.info("GET /requests  | userId = {}",  userId);
        return requestService.getAllByParticipationId(userId);
    }
}
