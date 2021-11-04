package com.project.xircle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "users")
@Data
public class User {
    @Id
    private Long id;
    private Integer age;
    private String adjective;
    @Column(value = "profile_img_src")
    private String profileImgSrc;
    private String gender;
    private String introduction;
    private String job;
    @Column(value = "display_name")
    private String displayName;
    private String email;
    private String password;
    private String address;
    private String university;
    @Column(value = "is_public")
    private Boolean isPublic;
    @Column(value = "is_graduate")
    private Boolean isGraduate;
    @Column(value = "phone_number")
    private String phoneNumber;
    @Column(value = "work_place")
    private String workPlace;
    private String resume;
    @Column(value = "is_location_public")
    private Boolean isLocationPublic;
    private Double longitude;
    private Double latitude;
    @Column(value = "created_at")
    private LocalDateTime createdAt;
}
