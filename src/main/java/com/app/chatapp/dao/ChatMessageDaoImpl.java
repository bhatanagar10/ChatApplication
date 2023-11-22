package com.app.chatapp.dao;

import com.app.chatapp.entity.Messages;
import com.app.chatapp.entity.UserRoom;
import com.app.chatapp.model.ChatMessage;
import com.app.chatapp.entity.Room;
import com.app.chatapp.entity.Users;
import com.app.chatapp.model.MessageType;
import com.app.chatapp.repository.MessageRepositoryJPA;
import com.app.chatapp.repository.RoomRepositoryJPA;
import com.app.chatapp.repository.UserRoomRepositoryJPA;
import com.app.chatapp.repository.UsersRepositoryJPA;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ChatMessageDaoImpl implements ChatMessageDao{

    @Autowired
    private MessageRepositoryJPA messageRepositoryJPA;

    @Autowired
    private UsersRepositoryJPA usersRepositoryJPA;

    @Autowired
    private RoomRepositoryJPA roomRepositoryJPA;

    @Autowired
    private UserRoomRepositoryJPA userRoomRepositoryJPA;

    @Override
    public List<Messages> findChatsByRoom(String room) {
        return messageRepositoryJPA.findAll();
    }

    @Override
    public void insertChatMessage(Messages message) {
        messageRepositoryJPA.save(message);
    }

    @Override
    public Users findUser(String user) {
        return usersRepositoryJPA.findByName(user);
    }

    @Override
    public Optional<Room> findRoom(Integer id) {
        return roomRepositoryJPA.findById(id);
    }

    @Override
    public Users insertUser(Users user ,Room room) {
        Users ruser=  usersRepositoryJPA.save(user);
        userRoomRepositoryJPA.save(new UserRoom(ruser , room , new Date()));

        return ruser;
    }

    @Override
    public Room insertRoom(Room room) {
        roomRepositoryJPA.save(room);
        return room;
    }
}
