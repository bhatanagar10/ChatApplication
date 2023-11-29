package com.app.chatapp.controller;

import com.app.chatapp.model.ChatMessage;
import com.app.chatapp.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Objects;

@RestController
public class ChatController{

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage){
        chatMessageService.insertChatMessage(chatMessage);
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoom() , chatMessage);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage , SimpMessageHeaderAccessor headerAccessor){
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username" , chatMessage.getSender());
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("room" , chatMessage.getRoom());

        delay();
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoom() , chatMessage);
    }

    @GetMapping("/chat.history")
    public ResponseEntity<List<ChatMessage>> history(@Payload String room){
        List<ChatMessage> chatMessages = chatMessageService.findChatsByRoom(room);
        return ResponseEntity.ok(chatMessages);
    }

    public void delay(){
        //delay to process the subscription
        long start = System.currentTimeMillis();
        long end = start + 1000;
        while (System.currentTimeMillis() < end);
    }
}
