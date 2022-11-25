package ru.practicum.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class NewEventDto {

    @Max(2000)
    @Min(20)
    @NotBlank
    String annotation;

    @Max(120)
    @Min(3)
    String title;

    @Max(7000)
    @Min(20)
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
