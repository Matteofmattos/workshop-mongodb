package com.matteof_mattos.workshopMongo.config;

import com.matteof_mattos.workshopMongo.models.entities.User;
import com.matteof_mattos.workshopMongo.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init(){

        userRepository.deleteAll();

        User user1 = new User(null,"Maria Lima","maria@gmail.com");
        User user2 = new User(null,"Alex Green","AlexxG@gmail.com");
        User user3 = new User(null,"Bobb Brown","bobbsBrown@gmail.com");

        userRepository.saveAll(Arrays.asList(user1,user2,user3));

    }


}
