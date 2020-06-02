package com.fernanda.comment_service.dto;

import com.fernanda.comment_service.domain.User;

public class UserDto {
    public Long id;
    public String name;

    public UserDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static UserDto fromDomain(User user) {
        return new UserDto(user.getId(), user.getName());
    }
}