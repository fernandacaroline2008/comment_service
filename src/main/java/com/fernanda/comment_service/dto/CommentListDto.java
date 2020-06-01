package com.fernanda.comment_service.dto;

import com.fernanda.comment_service.domain.Comment;

import java.util.List;

public class CommentListDto {
    public List<Comment> comments;

    public CommentListDto(List<Comment> comments) {
        this.comments = comments;
    }
}
