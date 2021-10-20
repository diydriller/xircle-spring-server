package com.project.xircle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table(value = "user_hashtag")
@AllArgsConstructor
@NoArgsConstructor
public class UserHashtag {
    @Id
    private Long id;
    @Column(value = "user_id")
    private Long userId;
    @Column(value = "hashtag_id")
    private Long hashtagId;
    @Column(value = "created_at")
    private LocalDateTime createdAt;

    public UserHashtag(Long userId,Long hashtagId){
        this.userId=userId;
        this.hashtagId=hashtagId;
    }
}
