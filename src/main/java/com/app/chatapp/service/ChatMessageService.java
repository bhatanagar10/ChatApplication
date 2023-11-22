package com.app.chatapp.service;

import com.app.chatapp.model.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatMessageService {
    public List<ChatMessage> findChatsByRoom(String room);
    public void insertChatMessage(ChatMessage chatMessage);
}
