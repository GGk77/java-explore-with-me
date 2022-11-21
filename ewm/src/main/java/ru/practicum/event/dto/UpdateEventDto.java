package ru.practicum.event.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UpdateEventDto {

    @NotNull
    Integer eventId;

    String annotation;

    String title;

    String description;

    // todo validations?
    LocalDateTime eventDate;

    Integer category;

    Boolean paid;

    Integer participantLimit;
}
