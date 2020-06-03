package com.fernanda.comment_service.repository;

import com.fernanda.comment_service.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.parent is null ORDER BY created DESC")
    List<Comment> findByUserId(Long userId);

    @Override
    @Query("SELECT c FROM Comment c WHERE c.parent is null ORDER BY created DESC")
    List<Comment> findAll();
}
