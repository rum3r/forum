package com.jeet.forum.services;

import com.jeet.forum.entities.Comment;
import com.jeet.forum.repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // Create a new comment
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Get all comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Get comment by ID
    public Optional<Comment> getCommentById(String id) {
        return commentRepository.findById(id);
    }

    // Get all comments for a post by postId
    public List<Comment> getCommentsByPostId(String postId) {
        return commentRepository.findByPostId(postId);
    }

    // Update an existing comment
    public Optional<Comment> updateComment(String id, Comment commentDetails) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setContent(commentDetails.getContent());
            return Optional.of(commentRepository.save(comment));
        }

        return Optional.empty();
    }

    // Delete a comment by ID
    public boolean deleteComment(String id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Like a comment or remove like if already liked
    public Optional<Comment> toggleLikeComment(String commentId, String userId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            if (comment.getLikedByUsers().contains(userId)) {
                comment.getLikedByUsers().remove(userId);
            } else {
                comment.getLikedByUsers().add(userId);
            }
            commentRepository.save(comment);
            return Optional.of(comment);
        }
        return Optional.empty();
    }
}