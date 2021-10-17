package com.project.xircle.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequestDto {

    @NotNull(message = "age is empty")
    private Integer age;
    @NotNull(message = "gender is empty")
    private String gender;
    @NotNull(message = "job is empty")
    private String job;
    @NotNull(message = "adjective is empty")
    private String adjective;
    @NotNull(message = "displayName is empty")
    private String displayName;
    @NotNull(message = "password is empty")
    private String password;
    @NotNull(message = "email is empty")
    private String email;
    private String address;
    @NotNull(message = "phoneNumber is empty")
    private String phoneNumber;
    @NotNull(message = "isPublic is empty")
    private Boolean isPublic;
    @NotNull(message = "isGraduate is empty")
    private Boolean isGraduate;
    @NotNull(message = "latitude is empty")
    private Double latitude;
    @NotNull(message = "longitude is empty")
    private Double longitude;
    @NotNull(message = "university is empty")
    private String university;
    @NotNull(message = "interestArr is empty")
    private List<String> interestArr;
}
