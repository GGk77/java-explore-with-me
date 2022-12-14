package ru.practicum.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.comment.service.CommentService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/comments")
public class AdminCommentController {

    @Autowired
    CommentService commentService;

    @DeleteMapping("/{comId}")
    public void adminDelete(@PathVariable Integer comId) {
        log.info("admin delete comment by id= {}", comId);
        commentService.adminDelete(comId);
    }
}
