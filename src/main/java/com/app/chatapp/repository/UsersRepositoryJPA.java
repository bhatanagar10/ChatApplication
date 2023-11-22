package com.app.chatapp.repository;

import com.app.chatapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepositoryJPA extends JpaRepository<Users, Integer> {
    public Users findByName(String name);
}
