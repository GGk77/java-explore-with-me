package ru.practicum.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.user.dto.UserDto;
import ru.practicum.user.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    UserDto create(@RequestBody @Valid UserDto userDto) {
        log.info("POST /admin/users: {}", userDto);
    return userService.create(userDto);
    }

    @DeleteMapping(value = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable Integer userId) {
        log.info("DELETE /admin/users/userId : {}", userId);
        userService.delete(userId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserDto> getAll(@RequestParam(name = "ids",required = false) List<Integer> userIds,
                         @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                         @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("GET all users");
        return userService.getAll(userIds, from, size);
    }


}
