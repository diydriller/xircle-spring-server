package com.project.xircle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "users_room")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoom {
    @Id
    private Long id;
    @Column(value = "user_id")
    private Long userId;
    @Column(value = "room_id")
    private Long roomId;
}
