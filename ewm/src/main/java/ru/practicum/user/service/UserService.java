package ru.practicum.user.service;

import ru.practicum.user.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto create(UserDto userDto);

    void delete(Integer userId);

    List<UserDto> getAll(List<Integer> userIds, Integer from, Integer size);

}
