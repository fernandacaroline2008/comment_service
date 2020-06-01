package com.fernanda.comment_service.dto;

import com.fernanda.comment_service.domain.Comment;
import com.fernanda.comment_service.domain.User;

public class CommentCreateDto {
    public String text;
    public Long userId;

    public CommentCreateDto() {
    }

    public CommentCreateDto(String text, Long userId) {
        this.text = text;
        this.userId = userId;
    }

    public Comment toDomain() {
        return new Comment(text, new User(userId));
    }

}