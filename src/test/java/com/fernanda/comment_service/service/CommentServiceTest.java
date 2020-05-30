package com.fernanda.comment_service.service;

import com.fernanda.comment_service.domain.Comment;
import com.fernanda.comment_service.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CommentServiceTest {

    private CommentRepository commentRepository;
    private CommentService commentService;

    @BeforeEach
    public void setup() {
        commentRepository = mock(CommentRepository.class);
        commentService = new CommentService(commentRepository);
    }

    @Test
    public void create_callsRepository() {
        Comment comment = new Comment(10L, "My text", LocalDateTime.now());

        when(commentRepository.save(comment)).thenReturn(comment);

        Comment result = commentService.create(comment);

        assertThat(result, is(comment));
        verify(commentRepository, times(1)).save(comment);
    }
}