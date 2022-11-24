package ru.practicum.event.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
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
