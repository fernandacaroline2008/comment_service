package com.fernanda.comment_service.service;

import com.fernanda.comment_service.domain.Comment;
import com.fernanda.comment_service.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<Comment> findById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public List<Comment> find(Optional<Long> userId) {
        return userId.map(commentRepository::findByUserId).orElse(commentRepository.findAll());
    }
}
