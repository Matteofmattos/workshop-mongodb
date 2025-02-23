package com.matteof_mattos.workshopMongo.controller;

import com.matteof_mattos.workshopMongo.models.dtos.PostDTO;
import com.matteof_mattos.workshopMongo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping
    public List<PostDTO> getAll_Posts() {
        return postService.getPosts();
    }


    @GetMapping(value = "/{id}")
    public PostDTO getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable String id) {

        postService.deletePostById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/titleSearch")
    public ResponseEntity<List<PostDTO>> getPostsByTitle(@RequestParam(value = "text", defaultValue = "")
                                       String text) {

        return ResponseEntity.ok(postService.getPostsByTitle(text));
    }


//    @PostMapping
//    public ResponseEntity<PostDTO> insertNewPost(@RequestBody PostDTO post){
//
//        PostDTO postResponse = postService.insertNewPost(post);
//
//        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/posts/{id}")
//                .buildAndExpand(postResponse.id())
//                .toUri();
//
//        return ResponseEntity.created(uri).body(postResponse);
//    }


//    @PutMapping(value = "/{id}")
//    public ResponseEntity<PostDTO> updatePost(@PathVariable String id,
//                                                 @RequestBody PostDTO postDto){
//
//        PostDTO postResponse = postService.updatePost(id,postDto);
//
//        return ResponseEntity.ok(postResponse);
//    }
}
