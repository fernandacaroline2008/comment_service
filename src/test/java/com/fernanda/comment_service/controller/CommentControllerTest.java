package com.fernanda.comment_service.controller;

import com.fernanda.comment_service.dto.CommentCreateDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    private static final String COMMENT_URI = "/api/v1/comments";
    private static final Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void create_whenCommentIsValid_returnSuccess() throws Exception {
        CommentCreateDto commentCreateDto = new CommentCreateDto("My first post");

        mockMvc.perform(post(COMMENT_URI).contentType(MediaType.APPLICATION_JSON).content(gson.toJson(commentCreateDto)))
               .andExpect(status().isCreated())
               .andExpect(content().string(gson.toJson(commentCreateDto)));
    }

    @Test
    public void create_whenCommentIsNotValid_returnError() throws Exception {
        mockMvc.perform(post(COMMENT_URI))
               .andExpect(status().isBadRequest());
    }
}