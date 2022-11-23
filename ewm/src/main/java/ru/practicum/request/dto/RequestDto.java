package ru.practicum.request.dto;

import lombok.*;
import ru.practicum.request.enums.Status;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestDto {

    Integer id;

    Integer eventId;

    Integer requestorId;

    Status status;

    LocalDateTime created;

}