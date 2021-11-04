package com.project.xircle.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Room extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
    private Long id;

    @OneToMany(mappedBy = "room")
    private List<UserRoom> userRoomList=new ArrayList<>();

    @OneToMany(mappedBy = "room")
    private List<Chat> chatList=new ArrayList<>();
}
