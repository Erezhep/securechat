package com.chat.securechat;

import com.chat.securechat.repository.MessageRepository;
import com.chat.securechat.controller.MessageRestController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MessageRestController.class)
@ContextConfiguration(classes = {MessageRestControllerTest.TestConfig.class, MessageRestController.class})
public class MessageRestControllerTest {

    @Configuration
    static class TestConfig {
        @Bean
        public MessageRepository messageRepository() {
            return Mockito.mock(MessageRepository.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void testGetLatestMessages() throws Exception {
        // Подготовим моковые данные — сущности сообщения (примерно)
        var sender = new com.chat.securechat.entity.User();
        sender.setUsername("testuser");

        var messageEntity = new com.chat.securechat.entity.Message();
        messageEntity.setSender(sender);
        messageEntity.setContent("Hello world");
        messageEntity.setCreatedAt(LocalDateTime.parse("2025-05-25T12:00:00Z"));

        when(messageRepository.findTop100ByOrderByCreatedAtAsc()).thenReturn(List.of(messageEntity));

        // Запрос к эндпоинту и проверка результата JSON
        mockMvc.perform(get("/api/messages/latest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sender").value("testuser"))
                .andExpect(jsonPath("$[0].content").value("Hello world"))
                .andExpect(jsonPath("$[0].timestamp").value("2025-05-25T12:00:00Z"));
    }
}

