package com.matteof_mattos.workshopMongo.controller;

import com.matteof_mattos.workshopMongo.models.dtos.PostDTO;
import com.matteof_mattos.workshopMongo.models.dtos.UserDTO;
import com.matteof_mattos.workshopMongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAll_Users() {
        return userService.getUsers();
    }


    @GetMapping(value = "/{id}")
    public UserDTO getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {

        userService.deleteUserById(id);

        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "myposts/{id}")
    public ResponseEntity<List<PostDTO>> getMyPosts(@PathVariable String id){

        return ResponseEntity.ok(userService.getUserPosts(id));
    }


    @PostMapping
    public ResponseEntity<UserDTO> insertNewUser(@RequestBody UserDTO user){

        UserDTO userResponse = userService.insertNewUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/{id}")
                .buildAndExpand(userResponse.id())
                .toUri();

        return ResponseEntity.created(uri).body(userResponse);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id,
                                                 @RequestBody UserDTO userDto){

        UserDTO userResponse = userService.updateUser(id,userDto);
        return ResponseEntity.ok(userResponse);
    }
}
