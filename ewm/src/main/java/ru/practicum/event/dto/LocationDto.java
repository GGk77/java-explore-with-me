package ru.practicum.event.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@ToString
public class LocationDto {

    @NotNull
    Double lat;

    @NotNull
    Double lon;
}
