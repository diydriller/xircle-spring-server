package com.project.xircle.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequestDto {

    @NotEmpty(message = "email is empty")
    @Pattern(regexp = "\\w+@\\w+.\\w+",message = "email format is wrong")
    private String email;

    @NotEmpty(message = "password is empty")
    private String password;

    @NotEmpty(message = "gender is empty")
    private String gender;

    @NotNull(message = "age is empty")
    private int age;

    @NotEmpty(message = "job is empty")
    private String job;

    @NotEmpty(message="displayName is empty")
    private String displayName;

    @NotEmpty(message="adjective is empty")
    private String adjective;

    @NotEmpty(message="introduction is empty")
    private String introduction;

    @NotEmpty(message="address is empty")
    private String address;

    @NotEmpty(message="university is empty")
    private String university;

    @NotNull(message="isPublic is empty")
    private boolean isPublic;

    @NotNull(message="isGraduate is empty")
    private boolean isGraduate;

    @NotEmpty(message = "phoneNumber is empty")
    @Pattern(regexp ="\\d{11}",message = "phoneNumber format is wrong")
    private String phoneNumber;

    private String workPlace;

    private String resume;

    @NotNull(message="isLocationPublic is empty")
    private  boolean isLocationPublic;

    private double latitude;

    private double longitude;

    private List<String> interestArr=new ArrayList<>();

    @NotNull(message="profileImg is empty")
    private MultipartFile profileImg;
}
