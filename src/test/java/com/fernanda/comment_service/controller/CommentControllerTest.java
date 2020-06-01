package com.fernanda.comment_service.controller;

import com.fernanda.comment_service.domain.Comment;
import com.fernanda.comment_service.domain.User;
import com.fernanda.comment_service.dto.CommentCreateDto;
import com.fernanda.comment_service.service.CommentService;
import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    private static final String COMMENT_URI = "/api/v1/comments";
    private static final Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    public void create_whenCommentIsValid_returnSuccess() throws Exception {
        CommentCreateDto commentCreateDto = new CommentCreateDto("My first post", 1l, "Toronto", 43.651070, -79.347015, 20.0);
        String body = gson.toJson(commentCreateDto);
        mockMvc.perform(post(COMMENT_URI).contentType(MediaType.APPLICATION_JSON).content(body))
               .andExpect(status().isCreated())
               .andExpect(content().string(CoreMatchers.containsString(commentCreateDto.text)));
    }

    @Test
    public void create_whenTextIsEmpty_returnError() throws Exception {
        CommentCreateDto commentCreateDto = new CommentCreateDto("", 1l);
        String body = gson.toJson(commentCreateDto);
        mockMvc.perform(post(COMMENT_URI).contentType(MediaType.APPLICATION_JSON).content(body))
               .andExpect(status().isBadRequest());
    }

    @Test
    public void create_whenUserIdIsNull_returnError() throws Exception {
        CommentCreateDto commentCreateDto = new CommentCreateDto("My first post", null);
        String body = gson.toJson(commentCreateDto);
        mockMvc.perform(post(COMMENT_URI).contentType(MediaType.APPLICATION_JSON).content(body))
               .andExpect(status().isBadRequest());
    }

    @Test
    public void reply_whenCommentIsValid_returnSuccess() throws Exception {
        User user = new User(1l);
        Comment parent = new Comment(1l, "My first post", user);
        when(commentService.findById(parent.getId())).thenReturn(Optional.of(parent));

        CommentCreateDto commentCreateDto = new CommentCreateDto("My reply", 1l);
        String body = gson.toJson(commentCreateDto);
        mockMvc.perform(post(COMMENT_URI + "/1/reply").contentType(MediaType.APPLICATION_JSON).content(body))
               .andExpect(status().isCreated())
               .andExpect(content().string(CoreMatchers.containsString(commentCreateDto.text)));
    }

    @Test
    public void reply_whenParentIsNotValid_returnError() throws Exception {
        when(commentService.findById(1l)).thenReturn(Optional.empty());

        CommentCreateDto commentCreateDto = new CommentCreateDto("My reply", 1l);
        String body = gson.toJson(commentCreateDto);
        mockMvc.perform(post(COMMENT_URI + "/1/reply").contentType(MediaType.APPLICATION_JSON).content(body))
               .andExpect(status().isBadRequest());
    }

    @Test
    public void find_withUserId_returnSuccess() throws Exception {
        User user = new User(1l);
        Comment comment1 = new Comment("Comment 1", user);
        Comment comment2 = new Comment("Comment 2", user);
        List<Comment> comments = Arrays.asList(comment1, comment2);
        when(commentService.find(Optional.of(1L))).thenReturn(comments);

        mockMvc.perform(get(COMMENT_URI + "?userId=1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.comments", hasSize(2)));
    }

    @Test
    public void find_withoutUserId_returnSuccess() throws Exception {
        User user1 = new User(1l);
        User user2 = new User(2l);
        Comment comment1 = new Comment("Comment 1", user1);
        Comment comment2 = new Comment("Comment 2", user1);
        Comment comment3 = new Comment("Comment 3", user2);
        List<Comment> comments = Arrays.asList(comment1, comment2, comment3);
        when(commentService.find(Optional.empty())).thenReturn(comments);

        mockMvc.perform(get(COMMENT_URI))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.comments", hasSize(3)));
    }
}