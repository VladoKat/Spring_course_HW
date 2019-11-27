package com.example.hw1.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    @JsonFormat(pattern = "uuuu-MM-dd HH:mm:ss")
    private LocalDateTime dateOfPublish = LocalDateTime.now();
    private String heading;
    private String author;
    private String text;
    private List<String> keywords;
    private String imgUrl;
    private boolean isActive = true;

    public Post(){}

    @JsonCreator
    @java.beans.ConstructorProperties({"id", "dateOfPublish", "heading", "author", "text", "keywords", "imgUrl", "isActive"})
    public Post(String id, LocalDateTime dateOfPublish, String heading,
                String author, String text, List<String> keywords,
                String imgUrl, boolean isActiveÒ) {
        this.id = id;
        this.dateOfPublish = dateOfPublish;
        this.heading = heading;
        this.author = author;
        this.text = text;
        this.keywords = keywords;
        this.imgUrl = imgUrl;
        this.isActive = isActive;
    }

}
