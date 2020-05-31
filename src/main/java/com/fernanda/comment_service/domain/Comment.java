package com.fernanda.comment_service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String text;

    private Long userId;

    public Comment() {
    }

    public Comment(String text, Long userId) {
        this.text = text;
        this.userId = userId;
    }

    public Comment(Long id, String text, Long userId, LocalDateTime created) {
        super(created);
        this.id = id;
        this.text = text;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Long getUserId() {
        return userId;
    }
}
