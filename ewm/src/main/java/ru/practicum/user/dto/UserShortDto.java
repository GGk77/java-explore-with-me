package ru.practicum.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link ru.practicum.user.model.User} entity
 */
@Data
@Builder
public class UserShortDto {

    @Size(max = 30)
    @NotNull
    String email;

    @Size(max = 20)
    @NotNull
    String name;
}