package com.fernanda.comment_service.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    public LocalDateTime getCreated() {
        return created;
    }

    public BaseEntity() {
    }

    public BaseEntity(LocalDateTime created) {
        this.created = created;
    }
}
