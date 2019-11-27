package com.example.hw1.web;

import com.example.hw1.model.Post;
import com.example.hw1.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService service;

    @GetMapping
    List<Post> getAllPosts(){
        return service.getPosts();
    }
    @GetMapping("{id}")
    Post getPostById(@PathVariable String id){
        return service.getPostById(id);
    }

    @PostMapping
    Post insertPost(@RequestBody Post post){
        return service.createPost(post);
    }

}
