package com.project.xircle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value = "chat")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Chat {
    @Id
    private Long id;
    @Column(value = "room_id")
    private Long roomId;
    @Column(value = "user_id")
    private Long userId;
    private String message;
    @Column(value = "created_at")
    private LocalDateTime createdAt;
}
