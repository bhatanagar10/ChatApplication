package com.app.chatapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users  {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public Users(String name) {
        this.name = name;
    }
}
