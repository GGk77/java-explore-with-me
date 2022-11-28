package ru.practicum.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserDto {

    Integer id;

    String email;

    String name;
}