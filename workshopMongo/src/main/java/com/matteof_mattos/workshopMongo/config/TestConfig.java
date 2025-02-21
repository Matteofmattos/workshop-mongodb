package com.matteof_mattos.workshopMongo.config;

import com.matteof_mattos.workshopMongo.models.embededs.Author;
import com.matteof_mattos.workshopMongo.models.embededs.Comment;
import com.matteof_mattos.workshopMongo.models.entities.Post;
import com.matteof_mattos.workshopMongo.models.entities.User;
import com.matteof_mattos.workshopMongo.repositories.PostRepository;
import com.matteof_mattos.workshopMongo.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @PostConstruct
    public void init(){

        userRepository.deleteAll();

        postRepository.deleteAll();

        User user1 = new User(null,"Maria Lima","maria@gmail.com");
        User user2 = new User(null,"Alex Green","AlexxG@gmail.com");
        User user3 = new User(null,"Bobb Brown","bobbsBrown@gmail.com");

        userRepository.saveAll(Arrays.asList(user1,user2,user3));

        Post post1 = new Post(null, new Author(user2), Instant.parse("2021-02-13T11:15:01Z"),
                "Partiu viagem!!", "Vou viajar para São Paulo. Abraços!");


        Post post2 = new Post(null, new Author(user1), Instant.parse("2021-02-14T10:05:49Z"),
                "Bom dia", "Acordei feliz hoje!");


        Comment c1 = new Comment("Boa viagem!", Instant.parse("2021-02-13T14:30:01Z"), new Author(user3));

        Comment c2 = new Comment("Aproveite!", Instant.parse("2021-02-13T15:38:05Z"), new Author(user1));

        Comment c3 = new Comment("Tenha um ótimo dia!", Instant.parse("2021-02-14T12:34:26Z"), new Author(user2));


        post1.getComments().add(c1);
        post1.getComments().add(c2);
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1,post2));


        user1.addPost(post2);
        userRepository.save(user1);

        user2.addPost(post1);
        userRepository.save(user2);
    }
}
