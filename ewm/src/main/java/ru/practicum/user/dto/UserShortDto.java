package ru.practicum.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserShortDto {

    @NotBlank
    @Email
    String email;

    @NotBlank
    String name;
}