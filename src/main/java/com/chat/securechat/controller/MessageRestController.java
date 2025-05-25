package com.chat.securechat.controller;


import com.chat.securechat.model.Message;
import com.chat.securechat.repository.MessageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    private final MessageRepository messageRepository;

    public MessageRestController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/latest")
    public List<Message> getLatestMessages() {
        return messageRepository.findTop100ByOrderByCreatedAtAsc().stream()
                .map(entity -> {
                    Message dto = new Message();
                    dto.setSender(entity.getSender().getUsername());
                    dto.setContent(entity.getContent());
                    dto.setTimestamp(entity.getCreatedAt().toString()); // ISO string
                    return dto;
                })
                .sorted(Comparator.comparing(Message::getTimestamp)) // от старых к новым
                .collect(Collectors.toList());
    }

}