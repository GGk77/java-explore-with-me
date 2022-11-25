package ru.practicum.request.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.error.exception.BadRequestException;
import ru.practicum.event.enums.EventState;
import ru.practicum.request.mapper.RequestMapper;
import ru.practicum.request.repository.RequestRepository;
import ru.practicum.event.model.Event;
import ru.practicum.event.service.EventService;
import ru.practicum.error.exception.NotFoundException;
import ru.practicum.request.dto.RequestDto;
import ru.practicum.request.enums.Status;
import ru.practicum.request.model.Request;
import ru.practicum.user.model.User;
import ru.practicum.user.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Transactional
    public RequestDto create(Integer userId, Optional<Integer> eventId) {
        log.debug("Create request, SERVICE");
        Event event = eventService.getEntityById(eventId.orElseThrow(() -> new NotFoundException("event with id= " + eventId + " not found")));
        Request request = requestRepository.save(Request.builder()
                .requester(userService.getEntityById(userId))
                .status((event.getModeration()) ? Status.PENDING : Status.CONFIRMED)
                .event(event)
                .build());
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new BadRequestException("event is not published");
        }
        if (event.getInitiator().getId().equals(userId)) {
            throw new BadRequestException("the initiator cannot make a request");
        }
        if (event.getParticipants().size() >= event.getParticipantLimit()) {
            throw new BadRequestException("participants limit");
        }
        if (requestRepository.existsByEvent_IdAndEvent_Initiator_Id(event.getId(), userId)) {
            throw new BadRequestException("request exist");
        }
        log.debug("request this id= {} create, SERVICE", request.getId());
        return RequestMapper.toRequestDto(request); //todo
    }

    @Transactional
    public RequestDto confirmRequestParticipation(Integer userId, Integer eventId, Integer reqId) {
        log.debug("Confirm request, SERVICE");
        Request request = getEntityById(reqId);
        Event event = eventService.getEntityById(eventId);
        checkUpdates(userId, event, request);
        if (event.getParticipants().size() == event.getParticipantLimit()) {
            throw new BadRequestException("participants limit");
        } else if (event.getParticipantLimit() - event.getParticipants().size() == 1) {
            requestRepository.saveAll(requestRepository.getByEvent_IdAndStatus(eventId, Status.PENDING)
                    .stream()
                    .peek(e -> e.setStatus(Status.CANCELED))
                    .collect(Collectors.toList()));
        }
        request.setStatus(Status.CONFIRMED);
        log.debug("request this id= {} confirm, SERVICE", reqId);
        return RequestMapper.toRequestDto(request);
    }

    @Transactional
    public RequestDto rejectRequestParticipation(Integer userId, Integer eventId, Integer reqId) {
        log.debug("Reject request, SERVICE");
        Event event = eventService.getEntityById(eventId);
        Request request = getEntityById(reqId);
        checkUpdates(userId, event, request);
        request.setStatus(Status.REJECTED);
        requestRepository.save(request);
        log.debug("request reject");
        return RequestMapper.toRequestDto(request);
    }

    @Override
    public List<RequestDto> getAllRequestsByInitiatorId(Integer userId, Integer eventId) {
        log.debug("GET all requests by initiator_id, SERVICE");
        List<Request> requestList = requestRepository.getByEvent_Initiator_IdAndEvent_Id(userId, eventId);
        return requestList.stream()
                .map(RequestMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public RequestDto cancelRequest(Integer userId, Integer requestId) {
        log.debug("Cancel request, SERVICE");
        User user = userService.getEntityById(userId);
        Request request = getEntityById(requestId);
        checkUpdates(userId, request.getEvent(), request);
        if (!request.getRequester().getId().equals(user.getId())) {
            throw new BadRequestException("cannot cancel another user's request");
        }
        requestRepository.save(request);
        log.debug("request cancel");
        return RequestMapper.toRequestDto(request);
    }

    @Override
    public List<RequestDto> getAllByParticipationId(Integer userId) {
        log.debug("Get all requests by id= {}, SERVICE", userId);
        List<Request> requestList = requestRepository.getByIdOrderById(userId);
        return requestList.stream()
                .map(RequestMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public Request getEntityById(Integer id) {
        log.debug("Get request by id= {}, SERVICE", id);
        return requestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("request with id =" + id + " not found"));
    }

    void checkUpdates(Integer userId, Event event, Request request) {
        User initiator = userService.getEntityById(userId);
        if (!event.getInitiator().getId().equals(initiator.getId())) {
            throw new BadRequestException("request error, user problem");
        }
        if (!request.getEvent().getId().equals(event.getId())) {
            throw new BadRequestException("this is a request for another event");
        }
        if (!request.getStatus().equals(Status.PENDING)) {
            throw new BadRequestException("this request is not in the pending state");
        }
        if (!event.getModeration() || event.getParticipantLimit() == 0) {
            throw new BadRequestException("forbidden update the request status");
        }
    }
}
