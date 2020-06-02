package com.fernanda.comment_service.controller;

import com.fernanda.comment_service.domain.Comment;
import com.fernanda.comment_service.dto.CommentCreateDto;
import com.fernanda.comment_service.dto.CommentDto;
import com.fernanda.comment_service.dto.CommentListDto;
import com.fernanda.comment_service.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<CommentDto> create(@RequestBody CommentCreateDto commentCreateDto) {
        if (commentCreateDto.text.isEmpty()) {
            LOG.error("Error creating comment: text is required.");
            return new ResponseEntity("Text is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if (commentCreateDto.userId == null) {
            LOG.error("Error creating comment: user id is required.");
            return new ResponseEntity("User id is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Comment comment = commentCreateDto.toDomain();
        commentService.create(comment);

        return new ResponseEntity(CommentDto.fromDomain(comment), HttpStatus.CREATED);
    }

    @PostMapping("/{commentId}/reply")
    public ResponseEntity<CommentDto> reply(@PathVariable(value = "commentId") Long commentId,
                                            @RequestBody CommentCreateDto commentCreateDto) {
        if (commentCreateDto.text.isEmpty()) {
            LOG.error("Error creating comment: text is required.");
            return new ResponseEntity("Text is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if (commentCreateDto.userId == null) {
            LOG.error("Error creating comment: user id is required.");
            return new ResponseEntity("User id is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Optional<Comment> parent = commentService.findById(commentId);
        if (!parent.isPresent()) {
            LOG.error("Error creating comment: Parent not found");
            return new ResponseEntity("Parent not found", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Comment comment = commentCreateDto.toDomain();
        comment.setParent(parent.get());
        commentService.create(comment);

        return new ResponseEntity(CommentDto.fromDomain(comment), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<CommentListDto> find(@RequestParam(value = "userId", required = false) Optional<Long> userId) {
        List<Comment> comments = commentService.find(userId);
        CommentListDto commentsDto = CommentListDto.fromDomain(comments);
        return new ResponseEntity(commentsDto, HttpStatus.OK);
    }
}