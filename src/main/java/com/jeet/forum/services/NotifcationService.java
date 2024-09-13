package com.jeet.forum.services;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class NotifcationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private Gson gson;

    @KafkaListener(topics = "${kafka.notification.topic}", groupId = "${kafka.group.id}")
    private void listenForNotification(Map<String, String> event) {
        log.info("Received notification event: {}", gson.toJson(event));
        messagingTemplate.convertAndSend("/topic/notifications", event);
    }
}
