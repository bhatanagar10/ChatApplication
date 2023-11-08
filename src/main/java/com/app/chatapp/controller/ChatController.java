package com.app.chatapp.controller;

import com.app.chatapp.model.ChatMessage;
import com.app.chatapp.repository.ChatMessageDataJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
public class ChatController {

    @Autowired
    private ChatMessageDataJPA repository;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
    public void sendMessage(@Payload ChatMessage chatMessage){
        repository.save(chatMessage);
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoom() , chatMessage);
    }

    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
    public void addUser(@Payload ChatMessage chatMessage , SimpMessageHeaderAccessor headerAccessor){
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username" , chatMessage.getSender());
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoom() , chatMessage);
    }

    @GetMapping("/chat.history")
    public List<ChatMessage> history(String room){
        return repository.findByRoom(room);
    }
}
