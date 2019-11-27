package com.example.hw1.service;

import com.example.hw1.model.Post;
import com.example.hw1.model.User;

import java.util.List;

public interface PostService {
    List<Post> getPosts();
    Post createPost(Post post);
    Post updatePost(String postId, Post Post);
    Post getPostById(String id);
    Post deletePost(String id);
}
