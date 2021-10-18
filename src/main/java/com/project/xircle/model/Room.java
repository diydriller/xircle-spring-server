package com.project.xircle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value = "room")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {
    @Id
    private Long id;
    private String type;
    @Column(value = "created_at")
    private LocalDateTime createdAt;
}
