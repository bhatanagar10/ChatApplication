package com.app.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
//    @Enumerated(EnumType.STRING)
    private MessageType type;
    private String content;
    private String sender;
    private String room;
}
