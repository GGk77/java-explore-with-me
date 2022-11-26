package ru.practicum.event.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@Jacksonized
@ToString
public class UpdateEventDto {

    @NotNull
    Integer eventId;

    String annotation;

    String title;

    String description;

    LocalDateTime eventDate;

    Integer category;

    Boolean paid;

    Integer participantLimit;
}
