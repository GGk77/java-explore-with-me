package ru.practicum.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class NewEventDto {

    @NotBlank
    String annotation;

    @NotBlank
    String title;

    @NotBlank
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
