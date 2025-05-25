package com.chat.securechat.controller;

import com.chat.securechat.entity.User;
import com.chat.securechat.model.Message;
import com.chat.securechat.repository.MessageRepository;
import com.chat.securechat.repository.UserRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public ChatWebSocketController(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")
    public Message sendMessage(Message messageDto) {
        User sender = userRepository.findByUsername(messageDto.getSender())
                .orElseThrow(() -> new RuntimeException("User not found: " + messageDto.getSender()));

        com.chat.securechat.entity.Message messageEntity = new com.chat.securechat.entity.Message();
        messageEntity.setSender(sender);
        messageEntity.setContent(messageDto.getContent());

        messageRepository.save(messageEntity);

        // Создаём DTO на основе сохранённой сущности с правильным временем
        Message responseDto = new Message();
        responseDto.setSender(sender.getUsername());
        responseDto.setContent(messageEntity.getContent());
        responseDto.setTimestamp(messageEntity.getCreatedAt().toString()); // ISO строка

        return responseDto;
    }
}
