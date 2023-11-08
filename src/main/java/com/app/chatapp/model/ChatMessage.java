package com.app.chatapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue
    private Integer id;
    @Transient
    private MessageType type;
    private String content;
    private String sender;
    private String room;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
