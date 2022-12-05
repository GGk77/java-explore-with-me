package ru.practicum.request.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.request.dto.RequestDto;
import ru.practicum.request.model.Request;

@Component
public class RequestMapper {
    public static RequestDto toRequestDto(Request request) {
        return RequestDto.builder()
                .id(request.getId())
                .event(request.getEvent().getId())
                .status(request.getStatus())
                .requester(request.getRequester().getId())
                .created(request.getCreated())
                .build();
    }
}
