package com.chat.securechat.repository;

import com.chat.securechat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findTop100ByOrderByCreatedAtAsc();
}
