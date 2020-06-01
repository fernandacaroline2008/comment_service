package com.fernanda.comment_service.dto;

import com.fernanda.comment_service.domain.Comment;
import com.fernanda.comment_service.domain.User;

public class CommentCreateDto {
    public String text;
    public Long userId;
    public String city;
    public Double latitude;
    public Double longitude;
    public Double temperature;

    public CommentCreateDto() {
    }

    public CommentCreateDto(String text, Long userId) {
        this.text = text;
        this.userId = userId;
    }

    public CommentCreateDto(String text, Long userId, String city, Double latitude, Double longitude, Double temperature) {
        this.text = text;
        this.userId = userId;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
    }

    public Comment toDomain() {
        return new Comment(text, new User(userId), city, latitude, longitude, temperature);
    }

}