package ru.practicum.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.comment.dto.CommentDto;
import ru.practicum.comment.service.CommentService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/comments")
@RequiredArgsConstructor
public class PublicCommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/{eventId}")
    public List<CommentDto> getPublicCommentsByEventId(@PathVariable Integer eventId) {
        log.info("GET /comments/{eventId} || eventId= {}", eventId);
        return commentService.getPublicCommentsByEventId(eventId);
    }
}
