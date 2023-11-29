package com.app.chatapp;

import com.app.chatapp.model.ChatMessage;
import com.app.chatapp.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatAppApplication implements CommandLineRunner {

    @Autowired
    private ChatMessageService chatMessageService;
    public static void main(String[] args) {
        SpringApplication.run(ChatAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("Hi");
        chatMessage.setSender("Aditya");
        chatMessage.setRoom("101");
        chatMessageService.insertChatMessage(chatMessage);
    }
}
