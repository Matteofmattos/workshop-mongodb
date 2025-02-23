package com.matteof_mattos.workshopMongo.service;

import com.matteof_mattos.workshopMongo.exceptions.ResourceNotFoundException;
import com.matteof_mattos.workshopMongo.models.dtos.PostDTO;
import com.matteof_mattos.workshopMongo.models.entities.Post;
import com.matteof_mattos.workshopMongo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.List;


@Service
public class PostService {

    private static final Logger log = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostDTO> getPosts(){

        List<Post> posts = postRepository.findAll();

        return posts.stream().map(post -> {
            return new PostDTO(post.getId(), post.getTitle(), post.getBody(), post.getMoment(), post.getAuthor(), post.getComments());
        }).toList();
    }


    @Transactional(readOnly = true)
    public PostDTO getPostById(String id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("# Resource not found"));

        return new PostDTO(post.getId(), post.getTitle(), post.getBody(), post.getMoment(), post.getAuthor(), post.getComments());
    }

    @Transactional(readOnly = true)
    public List<PostDTO> getPostsByTitle(String title){

        List<Post> postsByTitle = postRepository.findByTitleContainingIgnoreCase(title);

        return postsByTitle.stream().map(post -> {
            return new PostDTO(post.getId(), post.getTitle(), post.getBody(), post.getMoment(), post.getAuthor(), post.getComments());
        }).toList();

    }

//
//    @Transactional
//    public PostDTO insertNewPost(PostDTO postDTO){
//
//    }
//
//
//    @Transactional
//    public PostDTO updatePost(String id, PostDTO postDTO) {
//
//    }


    public void deletePostById(String id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("# Resource not found"));

        postRepository.deleteById(id);
    }
}
