package ru.practicum.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class AdminUpdateDto {

    String annotation;

    String title;

    String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    LocationDto location;

    Long category;

    Boolean paid;

    Boolean requestModeration;

    Integer participantLimit;
}
