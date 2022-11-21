package ru.practicum.event.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Data
@Builder
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
