package com.app.chatapp.service;

import com.app.chatapp.dao.ChatMessageDao;
import com.app.chatapp.entity.Messages;
import com.app.chatapp.entity.Room;
import com.app.chatapp.entity.Users;
import com.app.chatapp.model.ChatMessage;
import com.app.chatapp.model.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageServiceImpl implements ChatMessageService{

    @Autowired
    private ChatMessageDao chatMessageDao;
    @Override
    public List<ChatMessage> findChatsByRoom(String room) {
        List<Messages> messages=chatMessageDao.findChatsByRoom(room);
        List<ChatMessage> chatMessages = new ArrayList<>();

        for(Messages m : messages){
            ChatMessage chat = new ChatMessage(MessageType.CHAT , m.getContent() , m.getSender().getName() , m.getRoom().getId().toString());
            chatMessages.add(chat);
        }

        return chatMessages;
    }

    @Override
    public void insertChatMessage(ChatMessage chatMessage) {
        Users user = null;
        Room room = null;

        user = chatMessageDao.findUser(chatMessage.getSender());
        Optional<Room> r = chatMessageDao.findRoom(Integer.valueOf(chatMessage.getRoom()));

        if(r.isPresent())
            room = r.get();
        if(r.isEmpty()){
            room = chatMessageDao.insertRoom(new Room(Integer.valueOf(chatMessage.getRoom())));
        }
        if(user == null){
            user = chatMessageDao.insertUser(new Users(chatMessage.getSender()) , room);
        }
        Messages message = new Messages();
        message.setContent(chatMessage.getContent());
        message.setDate(new Date());
        message.setSender(user);
        message.setRoom(room);
        chatMessageDao.insertChatMessage(message);
    }

}
