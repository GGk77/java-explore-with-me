package ru.practicum.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
public class UserDto {

    private final Integer id;

    @Size(max = 30)
    @NotNull
    private final String email;

    @Size(max = 20)
    @NotNull
    private final String name;
}