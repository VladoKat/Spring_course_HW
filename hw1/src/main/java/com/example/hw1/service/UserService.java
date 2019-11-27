package com.example.hw1.service;

import com.example.hw1.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User createUser(User user);
    User updateUser(String userId, User user);
    User getUserById(String id);
    User deleteUser(String id);
}
