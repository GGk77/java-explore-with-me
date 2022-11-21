package ru.practicum.event.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AdminUpdateDto {

    String annotation;

    String title;

    String description;

    LocalDateTime eventDate;

    LocationDto location;

    Long category;

    Boolean paid;

    Boolean requestModeration;

    Integer participantLimit;
}
