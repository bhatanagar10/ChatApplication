package com.app.chatapp.dao;

import com.app.chatapp.entity.Messages;
import com.app.chatapp.model.ChatMessage;
import com.app.chatapp.entity.Room;
import com.app.chatapp.entity.Users;
import com.app.chatapp.model.MessageType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMessageDao {
    public List<Messages> findChatsByRoom(String room);
    public void insertChatMessage(Messages message);
    public Users findUser(String user);

    public Optional<Room> findRoom(Integer id);
    public Users insertUser(Users user  ,Room room);
    public Room insertRoom(Room room);
}
