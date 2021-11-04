package com.project.xircle.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value = "follow")
public class Follow {
    @Id
    private Long id;
    @Column(value = "member_id")
    private Long memberId;
    @Column(value = "target_member_id")
    private Long targetMemberId;
    @Column(value = "created_at")
    private LocalDateTime createdAt;
}
