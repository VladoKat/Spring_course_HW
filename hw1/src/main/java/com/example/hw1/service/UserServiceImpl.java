package com.example.hw1.service;

import com.example.hw1.dao.UserRepository;
import com.example.hw1.exception.EntityNotFoundException;
import com.example.hw1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repo;

    @Override
    public List<User> getUsers() {
        return repo.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return repo.findById(userId).orElseThrow(() -> new EntityNotFoundException("UserId not found"));
    }

    @Override
    public User findByUsername(String username) {
        return repo.findByEmail(username).orElseThrow(() -> new EntityNotFoundException("Username not found"));
    }

    @Override
    public User createUser(User user) {
        return repo.insert(user);
    }

    @Override
    public User updateUser(String userId, User user) {
        User old = repo.findById(userId).orElseThrow(() -> new EntityNotFoundException("UserId not found"));
        user.setId(userId);
        return repo.save(user);
    }

    @Override
//    @RolesAllowed("ROLE_USER")
    public User deleteUser(String id) {
        User old = repo.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Article with ID=%s not found.", id)));
        repo.deleteById(id);
        return old;
    }
}
