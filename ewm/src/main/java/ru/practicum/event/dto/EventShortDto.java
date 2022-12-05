package ru.practicum.event.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Getter
@Builder
@Jacksonized
@ToString
public class EventShortDto {

    Integer id;

    String annotation;

    String title;

    LocalDateTime eventDate;

    UserShortDto initiator;

    CategoryDto category;

    Integer confirmedRequests;

    Boolean paid;

    Integer views;
}
