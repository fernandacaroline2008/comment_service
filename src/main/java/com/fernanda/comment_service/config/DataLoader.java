package com.fernanda.comment_service.config;

import com.fernanda.comment_service.domain.Comment;
import com.fernanda.comment_service.domain.User;
import com.fernanda.comment_service.repository.CommentRepository;
import com.fernanda.comment_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class DataLoader {
    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, CommentRepository commentRepository) {
        return args -> {
            User user = new User("Fernanda");
            userRepository.save(user);

            User user2 = new User("Ane");
            userRepository.save(user2);

            Comment comment = new Comment("My first comment!", user);
            commentRepository.save(comment);

            Comment reply1Comment1 = new Comment("My reply 1 to comment 1!", user, Optional.of(comment));
            commentRepository.save(reply1Comment1);

            Comment reply2Comment1 = new Comment("My reply 2 comment 1!", user, Optional.of(comment));
            commentRepository.save(reply2Comment1);

            Comment replyReply1Comment1 = new Comment("One more!", user, Optional.of(reply1Comment1));
            commentRepository.save(replyReply1Comment1);

            Comment comment2 = new Comment("My first comment!", user2);
            commentRepository.save(comment2);
        };
    }
}
