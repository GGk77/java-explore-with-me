package ru.practicum.user.service;

import ru.practicum.user.dto.UserDto;
import ru.practicum.user.dto.UserShortDto;
import ru.practicum.user.model.User;

import java.util.List;

public interface UserService {

    UserDto create(UserShortDto userDto);

    void delete(Integer userId);

    List<UserDto> getAll(List<Integer> userIds, Integer from, Integer size);

    User getEntityById(Integer userId);
}
