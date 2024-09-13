package com.jeet.forum.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "posts")
@Data
@NoArgsConstructor
public class Post {
    @Id
    private String id;
    private String userId;
    private String title;
    private String content;
    private Set<String> likedByUsers = new HashSet<>();
    private Set<String> commentedByUsers = new HashSet<>();

    public Post(String userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
