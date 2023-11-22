
package com.app.chatapp.repository;

import com.app.chatapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepositoryJPA extends JpaRepository<Room, Integer> {
}