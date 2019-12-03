package com.example.hw1.web;

import com.example.hw1.model.Post;
import com.example.hw1.model.User;
import com.example.hw1.service.PostService;
import com.example.hw1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService service;

    @GetMapping
    ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.ok(service.getPosts());
    }
    @GetMapping("{id}")
    public ResponseEntity<Post> read(@PathVariable("id") String id) {
        Post foundPost = service.getPostById(id);
        if (foundPost == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundPost);
        }
    }

    @PostMapping
    ResponseEntity<Post> insertPost(@RequestBody Post post){
        Post createdPost = service.createPost(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPost.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(createdPost);
    }

    @DeleteMapping
    ResponseEntity<Post> deleteUser(@PathVariable("id") String id){
        Post foundPost = service.getPostById(id);
        if (foundPost == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.deletePost(id));
        }
    }

    @PutMapping
    ResponseEntity<Post> updatePost(@PathVariable("id") String id, Post post){
        Post foundPost = service.getPostById(id);
        if (foundPost == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.updatePost(id, post));
        }
    }

}
