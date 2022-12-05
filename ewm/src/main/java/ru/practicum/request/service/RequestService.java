package ru.practicum.request.service;

import ru.practicum.request.dto.RequestDto;
import ru.practicum.request.model.Request;

import java.util.List;
import java.util.Optional;

public interface RequestService {
    RequestDto create(Integer userId, Optional<Integer> eventId);

    RequestDto confirmRequestParticipation(Integer userId, Integer eventId, Integer reqId);

    RequestDto rejectRequestParticipation(Integer userId, Integer eventId, Integer reqId);

    List<RequestDto> getAllRequestsByInitiatorId(Integer userId, Integer eventId);

    RequestDto cancelRequest(Integer userId, Integer requestId);

    List<RequestDto> getAllByParticipationId(Integer userId);

    Request getEntityById(Integer id);
}
