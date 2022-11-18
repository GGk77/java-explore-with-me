package ru.practicum.user.service;

import ru.practicum.user.dto.UserDto;
import ru.practicum.user.dto.UserShortDto;

import java.util.List;

public interface UserService {

    UserDto create(UserShortDto usershortDto);

    void delete(Integer userId);

    List<UserDto> getAll(List<Integer> userIds, Integer from, Integer size);

}
