package com.project.xircle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value = "post")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    @Id
    private Long id;
    private String content;
    private String title;
    @Column(value = "post_img_src")
    private String postImgSrc;
    @Column(value = "created_at")
    private LocalDateTime createdAt;
}
