package com.example.hw1.web;

import com.example.hw1.model.User;
import com.example.hw1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    ResponseEntity<List<User>> getAllusers(){
       return ResponseEntity.ok(service.getUsers());
    }
    @GetMapping("{id}")
    public ResponseEntity<User> read(@PathVariable("id") String id) {
        User foundStudent = service.getUserById(id);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundStudent);
        }
    }

    @PostMapping
    ResponseEntity<User> insertUser(@RequestBody User user){
        User createdUser = service.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(createdUser);
    }

    @DeleteMapping
    ResponseEntity<User> deleteUser(@PathVariable("id") String id){
        User foundUser = service.getUserById(id);
        if (foundUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.deleteUser(id));
        }
    }

    @PutMapping
    ResponseEntity<User> updateUser(@PathVariable("id") String id, User user){
        User foundUser = service.getUserById(id);
        if (foundUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.updateUser(id, user));
        }
    }

}
