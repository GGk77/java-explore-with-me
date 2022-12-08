package ru.practicum.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.comment.dto.CommentDto;
import ru.practicum.comment.mapper.CommentMapper;
import ru.practicum.comment.model.Comment;
import ru.practicum.comment.repository.CommentRepository;
import ru.practicum.error.exception.BadRequestException;
import ru.practicum.error.exception.NotFoundException;
import ru.practicum.event.model.Event;
import ru.practicum.event.service.EventService;
import ru.practicum.user.model.User;
import ru.practicum.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Transactional
    public void adminDelete(Integer comId) {
        commentRepository.deleteById(comId);
        log.debug("Delete comment with id= {}, SERVICE", comId);
    }

    @Transactional
    public CommentDto create(Integer userId, Integer eventId, CommentDto commentDto) {
        log.debug("Create comment, SERVICE");
        User user = userService.getEntityById(userId);
        Event event = eventService.getEntityById(eventId);
        Comment comment = CommentMapper.toComment(commentDto, user, event);
        log.debug("Comment with id = {}, created", comment.getId());
        return CommentMapper.toCommentDto(commentRepository.save(comment));
    }

    @Transactional
    public CommentDto update(Integer userId, Integer comId, CommentDto updateText) {
        log.debug("Update comment, SERVICE");
        User user = userService.getEntityById(userId);
        Comment comment = getEntityById(comId);
        CommentDto dto = CommentMapper.toCommentDto(comment);
        if (!dto.getUserId().equals(user.getId())) {
            throw new BadRequestException("comment must belong to the user");
        }
        comment.setText(updateText.getText());
        log.debug("Comment with id= {}, updated", comment.getId());
        return CommentMapper.toCommentDto(commentRepository.save(comment));
    }

    @Transactional
    public void deleteCommentById(Integer userId, Integer comId) {
        log.debug("Delete comment with id= {}, SERVICE", comId);
        List<Comment> commentList = commentRepository.getUserCommentOrderByUserId(userId);
        for (Comment comment : commentList) {
            if (!comment.getId().equals(comId)) {
                throw new BadRequestException("comment must belong to the user");
            } else {
                commentRepository.deleteById(comId);
            }
        }
    }

    @Override
    public CommentDto getCommentById(Integer comId) {
        log.debug("Get comment by id= {}, SERVICE", comId);
        return CommentMapper.toCommentDto(commentRepository.findById(comId)
                .orElseThrow(() -> new NotFoundException("comment with id =" + comId + " not found")));
    }

    @Override
    public List<CommentDto> getAllCommentsInEvent(Integer eventId, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<Comment> commentList = commentRepository.getByEventIdOrderByCreatedDesc(eventId, pageable);
        return commentList.stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    public Comment getEntityById(Integer id) {
        log.debug("Get comment by id= {}, SERVICE", id);
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("comment with id =" + id + " not found"));
    }
}
