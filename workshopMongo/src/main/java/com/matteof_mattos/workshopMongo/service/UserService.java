package com.matteof_mattos.workshopMongo.service;

import com.matteof_mattos.workshopMongo.exceptions.ResourceNotFoundException;
import com.matteof_mattos.workshopMongo.models.dtos.PostDTO;
import com.matteof_mattos.workshopMongo.models.dtos.UserDTO;
import com.matteof_mattos.workshopMongo.models.entities.Post;
import com.matteof_mattos.workshopMongo.models.entities.User;
import com.matteof_mattos.workshopMongo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public List<UserDTO> getUsers(){

        List<User> users = userRepository.findAll();

        return users.stream().map(user -> {
            return new UserDTO(user.getId(), user.getName(), user.getEmail());
        }).toList();
    }


    @Transactional(readOnly = true)
    public List<PostDTO> getUserPosts(String id){

        List<Post> posts = new ArrayList<>();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("# Resource not found."));

        return user.getPosts().stream().map(post -> {

             return new PostDTO(post.getId(),
                     post.getTitle(),
                     post.getBody(),
                     post.getMoment(),
                     post.getAuthor(),
                     post.getComments());

        }).toList();
    }


    @Transactional(readOnly = true)
    public UserDTO getUserById(String id) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("# Resource not found."));
        return new UserDTO(user.getId(), user.getName(),user.getEmail());
    }


    @Transactional
    public UserDTO insertNewUser(UserDTO userDTO){

        User user = userRepository.save(new User(null, userDTO.nome(), userDTO.email()));
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }


    @Transactional
    public UserDTO updateUser(String id, UserDTO userDTO) {

        User entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("# Resource not found."));

        entity.setName(userDTO.nome());
        entity.setEmail(userDTO.email());

        entity = userRepository.save(entity);

        return new UserDTO(entity.getId(),entity.getName(),entity.getEmail());
    }


    public void deleteUserById(String id) {

        User entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("# Resource not found."));

        userRepository.deleteById(id);

    }
}
