package ru.practicum.event.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.event.enums.EventState;
import ru.practicum.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Getter
@Builder
@Jacksonized
@ToString
public class EventDto {

    Integer id;

    String annotation;

    String title;

    String description;

    EventState state;

    LocalDateTime createdOn;

    LocalDateTime publishedOn;

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
