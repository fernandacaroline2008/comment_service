package com.fernanda.comment_service.controller;

import com.fernanda.comment_service.domain.Comment;
import com.fernanda.comment_service.dto.CommentCreateDto;
import com.fernanda.comment_service.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping()
    public ResponseEntity<Comment> create(@RequestBody CommentCreateDto commentCreateDto) {
        Comment comment = commentCreateDto.toDomain();

        if (comment.getText().isEmpty()) {
            LOG.error("Error creating comment: text is required.");
            return new ResponseEntity("Text is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if (comment.getUserId() == null) {
            LOG.error("Error creating comment: user id is required.");
            return new ResponseEntity("User id is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        commentService.create(comment);

        return new ResponseEntity(comment, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Comment>> find(@RequestParam(value = "userId", required = false) Optional<Long> userId) {
        List<Comment> comments = commentService.find(userId);

        return new ResponseEntity(comments, HttpStatus.OK);
    }
}