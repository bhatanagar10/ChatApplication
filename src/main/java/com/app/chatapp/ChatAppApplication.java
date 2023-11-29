package com.app.chatapp;

import com.app.chatapp.model.ChatMessage;
import com.app.chatapp.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatAppApplication {

    @Autowired
    private ChatMessageService chatMessageService;
    public static void main(String[] args) {
        SpringApplication.run(ChatAppApplication.class, args);
    }
}
