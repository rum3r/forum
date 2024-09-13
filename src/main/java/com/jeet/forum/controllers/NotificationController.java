package com.jeet.forum.controllers;

import com.jeet.forum.services.NotifcationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class NotificationController {
    @Autowired
    private NotifcationService notifcationService;


    // Event detection for when a user likes a post
//    @PostMapping("/like/post/{postId}/user/{userId}")
//    public void handlePostLike(@PathVariable String postId, @PathVariable String userId) {
//        notifcationService.handlePostLike(postId, userId);
//    }
//
//    // Event detection for when a user likes a comment
//    @PostMapping("/like/comment/{commentId}/user/{userId}")
//    public void handleCommentLike(@PathVariable String commentId, @PathVariable String userId) {
//        notifcationService.handleCommentLike(commentId, userId);
//    }
//
//    // WebSocket message mapping to receive and process real-time messages
//    @MessageMapping("/events")
//    public void receiveEvent(Map<String, String> event) {
//        // You can process incoming events here if necessary
//        System.out.println("Received WebSocket event: " + event);
//    }
}
