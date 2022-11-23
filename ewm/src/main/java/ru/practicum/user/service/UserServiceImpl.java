package ru.practicum.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exception.NotFoundException;
import ru.practicum.user.dto.UserDto;
import ru.practicum.user.mapper.UserMapper;
import ru.practicum.user.model.User;
import ru.practicum.user.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserDto create(UserDto userDto) {
        log.debug("Create user, SERVICE");
        User user = userRepository.save(UserMapper.toUser(userDto));
        log.debug("User with id = {}, created", user.getId());
        return UserMapper.toUserDto(user);
    }

    @Transactional
    public void delete(Integer userId) {
        log.debug("Delete user with id={}, SERVICE", userId);
        userRepository.delete(userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id =" + userId + " not found")));
    }

    @Override
    public List<UserDto> getAll(List<Integer> userIds, Integer from, Integer size) {
        log.debug("Get all users, SERVICE");
        Pageable pageable = PageRequest.of(from / size, size);
        if (userIds.isEmpty()) {
            return userRepository.findAll(pageable)
                    .stream()
                    .map(UserMapper::toUserDto)
                    .collect(Collectors.toList());
        } else return userRepository.getByIdOrderByIdAsc(userIds, pageable)
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    public User getEntityById(Integer id) {
        return  userRepository.findById(id).orElseThrow(() -> new NotFoundException("user with id =" + id + " not found"));
    }
}
