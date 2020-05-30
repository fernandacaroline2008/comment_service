package com.fernanda.comment_service.controller;

import com.fernanda.comment_service.dto.CommentCreateDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @PostMapping()
    public ResponseEntity<CommentCreateDto> create(@RequestBody CommentCreateDto commentCreateDto) {
        if (commentCreateDto.text.isEmpty()) {
            return new ResponseEntity("Text is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(commentCreateDto, HttpStatus.CREATED);
    }
}
