package com.example.hw1.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String imgUrl;

    public User(){}

    @JsonCreator
    @java.beans.ConstructorProperties({"id", "firstName", "lastName", "email", "password", "role", "imgUrl"})
    public User(String id, String firstName, String lastName, String email, String password, String role, String imgUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.imgUrl = imgUrl;
    }

}
