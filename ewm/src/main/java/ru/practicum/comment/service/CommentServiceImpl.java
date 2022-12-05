package ru.practicum.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.comment.dto.CommentDto;
import ru.practicum.comment.repository.CommentRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void adminDelete(Integer comId) {

    }

    @Override
    public List<CommentDto> getPublicCommentsByEventId(Integer userId) {
        return null;
    }

    @Override
    public CommentDto create(Integer userId, Integer eventId, CommentDto commentDto) {
        return null;
    }

    @Override
    public CommentDto update(Integer userId, Integer comId, CommentDto commentDto) {
        return null;
    }

    @Override
    public void deleteCommentById(Integer userId, Integer comId) {

    }

    @Override
    public CommentDto getCommentById(Integer comId) {
        return null;
    }

    @Override
    public List<CommentDto> getAllCommentInEvent(Integer eventId) {
        return null;
    }
}
