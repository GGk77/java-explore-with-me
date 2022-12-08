package ru.practicum.comment.service;

import ru.practicum.comment.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void adminDelete(Integer comId);

    CommentDto create(Integer userId, Integer eventId, CommentDto commentDto);

    CommentDto update(Integer userId, Integer comId, CommentDto commentDto);

    void deleteCommentById(Integer userId, Integer comId);

    CommentDto getCommentById(Integer comId);

    List<CommentDto> getAllCommentsInEvent(Integer eventId, Integer from, Integer size);
}
