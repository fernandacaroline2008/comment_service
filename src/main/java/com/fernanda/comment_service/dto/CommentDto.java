package com.fernanda.comment_service.dto;

import com.fernanda.comment_service.domain.Comment;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommentDto {
    public Long id;
    public String text;
    public UserDto user;
    public String city;
    public Double latitude;
    public Double longitude;
    public Double temperature;
    public String created;
    public List<CommentDto> replies;

    public CommentDto(Long id,
                      String text,
                      UserDto user,
                      String city,
                      Double latitude,
                      Double longitude,
                      Double temperature,
                      String created, List<CommentDto> replies) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.created = created;
        this.replies = replies;
    }

    public static CommentDto fromDomain(Comment comment) {
        return new CommentDto(comment.getId(), comment.getText(), UserDto.fromDomain(comment.getUser()), comment.getCity(),
                              comment.getLatitude(), comment.getLongitude(), comment.getTemperature(),
                              Optional.ofNullable(comment.getCreated()).map(LocalDateTime::toString).orElse(null),
                              Optional.ofNullable(comment.getReplies())
                                      .map(replies -> replies.stream()
                                                             .map(CommentDto::fromDomain)
                                                             .collect(Collectors.toList()))
                                      .orElse(Collections.emptyList()));
    }
}