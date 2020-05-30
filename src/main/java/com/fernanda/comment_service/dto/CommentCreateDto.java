package com.fernanda.comment_service.dto;

public class CommentCreateDto {
    public String text;

    public CommentCreateDto() {
        super();
    }

    public CommentCreateDto(String text) {
        this.text = text;
    }

}