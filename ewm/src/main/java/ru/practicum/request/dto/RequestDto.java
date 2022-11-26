package ru.practicum.request.dto;

import lombok.*;
import ru.practicum.request.enums.Status;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestDto {

    Integer id;

    Integer event;

    Integer requester;

    Status status;

    LocalDateTime created;

}