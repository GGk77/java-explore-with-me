package ru.practicum.event.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
public class NewEventDto {

    @NotBlank
    @Size(min = 20, max = 2000)
    String annotation;

    @NotBlank
    @Size(min = 3, max = 120)
    String title;

    @NotBlank
    @Size(min = 20, max = 7000)
    String description;

    @NotNull
            //todo validation
    LocalDateTime eventDate;

    @NotNull
    LocationDto location;

    @NotNull
    Integer category;

    @NotNull
    Boolean paid;

    Integer participantLimit;

    Boolean requestModeration;
}
