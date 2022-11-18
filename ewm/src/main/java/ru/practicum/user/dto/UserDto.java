package ru.practicum.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
public class UserDto {

    Integer id;

    @Size(max = 30)
    @NotNull
    String email;

    @Size(max = 20)
    @NotNull
    String name;
}