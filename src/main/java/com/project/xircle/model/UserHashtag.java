package com.project.xircle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value = "users_hashtag")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserHashtag {
    @Id
    private Long id;
    @Column(value = "user_id")
    private Long userId;
    @Column(value = "hashtag_id")
    private Long hashtagId;
    @Column(value = "created_at")
    private LocalDateTime createdAt;
}
