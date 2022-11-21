package ru.practicum.event.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class LocationDto {

    @NotNull
    Double lat;

    @NotNull
    Double lon;
}
