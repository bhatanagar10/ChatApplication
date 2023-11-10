package com.app.chatapp.repository;

import com.app.chatapp.model.ChatMessage;
import com.app.chatapp.model.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageDataJPA extends JpaRepository<ChatMessage , Integer> {
    public List<ChatMessage> findByRoomAndType(String room , MessageType type);
}
