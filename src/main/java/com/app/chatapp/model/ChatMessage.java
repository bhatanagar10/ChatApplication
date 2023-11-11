package com.app.chatapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue
    private Integer id;
//    @Enumerated(EnumType.STRING)
    @Transient
    private MessageType type;
    private String content;
    private String sender;
    private String room;


}
