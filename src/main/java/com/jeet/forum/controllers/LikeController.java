package com.jeet.forum.controllers;

import com.jeet.forum.entities.Comment;
import com.jeet.forum.entities.Post;
import com.jeet.forum.services.CommentService;
import com.jeet.forum.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    // Toggle like for a post by ID and userId
    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<String> toggleLikePost(@PathVariable String postId, @PathVariable String userId) {
        Optional<Post> updatedPost = postService.toggleLikePost(postId, userId);
        if (updatedPost.isPresent()) {
            if (updatedPost.get().getLikedByUsers().contains(userId)) {
                return ResponseEntity.ok("Post liked successfully.");
            } else {
                return ResponseEntity.ok("Like removed from post.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Toggle like for a comment by ID and userId
    @PostMapping("/comment/{commentId}/user/{userId}")
    public ResponseEntity<String> toggleLikeComment(@PathVariable String commentId, @PathVariable String userId) {
        Optional<Comment> updatedComment = commentService.toggleLikeComment(commentId, userId);
        if (updatedComment.isPresent()) {
            if (updatedComment.get().getLikedByUsers().contains(userId)) {
                return ResponseEntity.ok("Comment liked successfully.");
            } else {
                return ResponseEntity.ok("Like removed from comment.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
