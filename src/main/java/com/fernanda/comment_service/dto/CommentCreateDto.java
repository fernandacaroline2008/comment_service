package com.fernanda.comment_service.dto;

import com.fernanda.comment_service.domain.Comment;

public class CommentCreateDto {
    public String text;

    public CommentCreateDto() {
    }

    public CommentCreateDto(String text) {
        this.text = text;
    }

    public Comment toDomain() {
        return new Comment(text);
    }

}