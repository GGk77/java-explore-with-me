package ru.practicum.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class UpdateEventDto {

    @NotNull
    Integer eventId;

    String annotation;

    String title;

    String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    Integer category;

    Boolean paid;

    Integer participantLimit;
}
