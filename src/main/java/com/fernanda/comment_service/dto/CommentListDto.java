package com.fernanda.comment_service.dto;

import com.fernanda.comment_service.domain.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentListDto {
    public List<CommentDto> comments;

    public CommentListDto(List<CommentDto> comments) {
        this.comments = comments;
    }

    public static CommentListDto fromDomain(List<Comment> comments) {
        return new CommentListDto(comments.stream().map(CommentDto::fromDomain).collect(Collectors.toList()));
    }
}
