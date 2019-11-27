package com.example.hw1.service;

import com.example.hw1.dao.PostRepository;
import com.example.hw1.dao.UserRepository;
import com.example.hw1.exception.EntityNotFoundException;
import com.example.hw1.model.Post;
import com.example.hw1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostRepository repo;

    @Override
    public List<Post> getPosts() {
        return repo.findAll();
    }

    @Override
    public Post getPostById(String postId) {
        return repo.findById(postId).get();
    }

    @Override
    public Post createPost(Post post) {
        return repo.insert(post);
    }

    @Override
    public Post updatePost(String postId, Post post) {
        return repo.save(post);
    }

    @Override
//    @RolesAllowed("ROLE_USER")
    public Post deletePost(String id) {
        Post old = repo.findById(id).orElseThrow( () ->
                new EntityNotFoundException(String.format("Article with ID=%s not found.", id)));
        repo.deleteById(id);
        return old;
    }
}
