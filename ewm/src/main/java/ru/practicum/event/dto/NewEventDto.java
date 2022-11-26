package ru.practicum.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Builder
@Jacksonized
@ToString
public class NewEventDto {

    @Max(2000)
    @Min(20)
    @NotBlank
    @Size(min = 20, max = 2000)
    String annotation;

    @Max(120)
    @Min(3)
    @Size(min = 3, max = 120)
    String title;

    @Max(7000)
    @Min(20)
    @NotBlank
    @Size(min = 20, max = 7000)
    String description;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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
