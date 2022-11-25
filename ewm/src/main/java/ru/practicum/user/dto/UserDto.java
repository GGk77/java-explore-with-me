package ru.practicum.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    Integer id;


    String email;


    String name;
}