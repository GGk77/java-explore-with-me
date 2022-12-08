package ru.practicum.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comment.dto.CommentDto;
import ru.practicum.comment.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users/{userId}/comments")
@RequiredArgsConstructor
public class PrivateCommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/{eventId}")
    public CommentDto create(@PathVariable Integer userId,
                             @PathVariable Integer eventId,
                             @RequestBody @Valid CommentDto commentDto) {
        log.info("POST /users/{userId}/comments/eventId || userId= {} || eventId= {} || {}", userId, eventId, commentDto);
        return commentService.create(userId, eventId, commentDto);
    }

    @PatchMapping("/{comId}")
    public CommentDto update(@PathVariable Integer userId,
                             @PathVariable Integer comId,
                             @RequestBody @Valid CommentDto commentDto) {
        log.info("PATCH /users/{userId}/comments/comId || userId= {} || comId= {} || {}", userId, comId, commentDto);
        return commentService.update(userId, comId, commentDto);
    }

    @DeleteMapping("/{comId}")
    public void deleteCommentById(@PathVariable Integer userId,
                                  @PathVariable Integer comId) {
        log.info("DELETE /users/{userId}/comments/comId || userId= {} || comId= {}", userId, comId);
        commentService.deleteCommentById(userId, comId);
    }

    @GetMapping("/{comId}")
    public CommentDto getCommentById(@PathVariable Integer userId,
                                     @PathVariable Integer comId) {
        log.info("GET /users/{userId}/comments/{comId} || userId= {} || comId= {}", userId, comId);
        return commentService.getCommentById(comId);
    }

    @GetMapping
    public List<CommentDto> getAllCommentsInEvent(@RequestParam Integer eventId,
                                                  @RequestParam(defaultValue = "0") Integer from,
                                                  @RequestParam(defaultValue = "10") Integer size) {
        log.info("GET /users/{userId}/comments/{eventId}/ || eventId= {}", eventId);
        return commentService.getAllCommentsInEvent(eventId, from, size);
    }

}
