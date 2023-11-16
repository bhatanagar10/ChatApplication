package com.app.chatapp.controller;

import com.app.chatapp.model.ChatMessage;
import com.app.chatapp.model.MessageType;
import com.app.chatapp.repository.ChatMessageDataJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class ChatController {

    @Autowired
    private ChatMessageDataJPA repository;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage){
        repository.save(chatMessage);
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoom() , chatMessage);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage , SimpMessageHeaderAccessor headerAccessor){
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username" , chatMessage.getSender());
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("room" , chatMessage.getRoom());

        //delay to process the subscription
        long start = System.currentTimeMillis();
        long end = start + 1000;
        while (System.currentTimeMillis() < end);
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoom() , chatMessage);
    }

    @GetMapping("/chat.history")
    public ResponseEntity<List<ChatMessage>> history(@Payload String room){
        List<ChatMessage> chatMessages =  repository.findByRoom(room);
        return ResponseEntity.ok(chatMessages);
    }
}
