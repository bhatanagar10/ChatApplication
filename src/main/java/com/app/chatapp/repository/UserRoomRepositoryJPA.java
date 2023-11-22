package com.app.chatapp.repository;

import com.app.chatapp.entity.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoomRepositoryJPA extends JpaRepository<UserRoom,Integer > {
}
