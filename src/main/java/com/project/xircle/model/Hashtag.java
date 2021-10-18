package com.project.xircle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value = "hashtag")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hashtag {
    @Id
    private Long id;
    private String hashtag;
    @Column(value = "post_id")
    private Long postId;
    @Column(value = "created_at")
    private LocalDateTime createdAt;
}
