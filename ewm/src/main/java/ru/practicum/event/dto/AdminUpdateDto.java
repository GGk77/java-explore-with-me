package ru.practicum.event.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Getter
@Builder
@Jacksonized
@ToString
public class AdminUpdateDto {

    String annotation;

    String title;

    String description;

    LocalDateTime eventDate;

    LocationDto location;

    Integer category;

    Boolean paid;

    Boolean requestModeration;

    Integer participantLimit;
}
