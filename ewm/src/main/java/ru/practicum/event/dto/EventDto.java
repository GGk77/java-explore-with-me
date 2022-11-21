package ru.practicum.event.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.event.enums.EventState;
import ru.practicum.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {

    Integer id;

    String annotation;

    String title;

    String description;

    EventState state;

    LocalDateTime created;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDateTime published;

    LocalDateTime eventDate;

    UserShortDto initiator;

    LocationDto location;

    CategoryDto category;

    Integer confirmedRequests;

    Boolean paid;

    Boolean requestModeration;

    Integer participantLimit;

    Integer views;
}
