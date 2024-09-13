package com.jeet.forum.services;

import com.jeet.forum.entities.Post;
import com.jeet.forum.repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Create a new post
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // Get all posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Get post by ID
    public Optional<Post> getPostById(String id) {
        return postRepository.findById(id);
    }

    // Get all posts by userId
    public List<Post> getPostsByUserId(String userId) {
        return postRepository.findByUserId(userId);
    }

    // Update an existing post
    public Optional<Post> updatePost(String id, Post postDetails) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            return Optional.of(postRepository.save(post));
        }

        return Optional.empty();
    }

    // Delete a post by ID
    public boolean deletePost(String id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Like a post or remove like if already liked
    public Optional<Post> toggleLikePost(String postId, String userId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            if (post.getLikedByUsers().contains(userId)) {
                post.getLikedByUsers().remove(userId);
            } else {
                post.getLikedByUsers().add(userId);
            }
            postRepository.save(post);
            return Optional.of(post);
        }
        return Optional.empty();
    }
}
