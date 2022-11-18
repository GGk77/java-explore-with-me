package ru.practicum.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exception.NotFoundException;
import ru.practicum.user.dto.UserDto;
import ru.practicum.user.dto.UserShortDto;
import ru.practicum.user.mapper.UserMapper;
import ru.practicum.user.model.User;
import ru.practicum.user.repositories.UserRepository;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserDto create(UserShortDto usershortDto) {
        User user = userRepository.save(UserMapper.toUser(usershortDto));
        log.debug("User with id = {}, created", user.getId());
        return UserMapper.toUserDto(user);
    }

    @Transactional
    public void delete(Integer userId) {
    userRepository.delete(userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User with id =" + userId + " not found")));
    }

    @Override
    public List<UserDto> getAll(List<Integer> userIds, Integer from, Integer size) {
        return null;
    }

}
