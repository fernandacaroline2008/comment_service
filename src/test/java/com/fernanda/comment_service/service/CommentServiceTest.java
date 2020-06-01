package com.fernanda.comment_service.service;

import com.fernanda.comment_service.domain.Comment;
import com.fernanda.comment_service.domain.User;
import com.fernanda.comment_service.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        Comment comment = createComment();

        when(commentRepository.save(comment)).thenReturn(comment);

        Comment result = commentService.create(comment);

        assertThat(result, is(comment));
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    public void find_whenUserIdIsPresent_callsRepository() {
        Comment comment1 = createComment();
        Comment comment2 = createComment();
        List<Comment> comments = Arrays.asList(comment1, comment2);
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> result = commentService.find(Optional.empty());

        assertThat(result, is(comments));
        verify(commentRepository, times(1)).findAll();
    }

    @Test
    public void find_whenUserIdIsEmpty_callsRepository() {
        Comment comment1 = createComment();
        Comment comment2 = createComment();
        List<Comment> comments = Arrays.asList(comment1, comment2);
        when(commentRepository.findByUserId(1L)).thenReturn(comments);

        List<Comment> result = commentService.find(Optional.of(1L));

        assertThat(result, is(comments));
        verify(commentRepository, times(1)).findByUserId(1L);
    }

    private Comment createComment() {
        User user = new User(1L);
        return new Comment(1L, "My text", user, Optional.empty(), LocalDateTime.now());
    }
}