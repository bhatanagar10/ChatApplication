package com.app.chatapp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class UserRoom {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Users user;
    @ManyToOne
    private Room room;
    private Date dateOfJoining;

    public UserRoom(Users user, Room room, Date dateOfJoining) {
        this.user = user;
        this.room = room;
        this.dateOfJoining = dateOfJoining;
    }
}
