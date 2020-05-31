package com.fernanda.comment_service.config;

import com.fernanda.comment_service.domain.Comment;
import com.fernanda.comment_service.domain.User;
import com.fernanda.comment_service.repository.CommentRepository;
import com.fernanda.comment_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, CommentRepository commentRepository) {
        return (args) -> {
            User user = new User("Fernanda");
            userRepository.save(user);

            commentRepository.save(new Comment("My first comment!", user.getId()));
            commentRepository.save(new Comment("My second comment!", user.getId()));
        };
    }
}
