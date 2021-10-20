package com.project.xircle.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDto {

    @NotEmpty(message = "displayName is empty")
    private String displayName;
    @NotEmpty(message = "password is empty")
    private String password;
    @NotNull(message = "age is empty")
    private Integer age;
    @NotEmpty(message = "gender is empty")
    private String gender;
    @NotEmpty(message = "job is empty")
    private String job;
    @NotEmpty(message = "adjective is empty")
    private String adjective;
    @NotEmpty(message = "email is empty")
    private String email;
    @NotEmpty(message = "address is empty")
    private String address;
    @NotEmpty(message = "phoneNumber is empty")
    private String phoneNumber;
    @NotNull(message = "isPublic is empty")
    private Boolean isPublic;
    @NotNull(message = "isGraduate is empty")
    private Boolean isGraduate;
    @NotNull(message = "latitude is empty")
    private Double latitude;
    @NotNull(message = "longitude is empty")
    private Double longitude;
    @NotEmpty(message = "university is empty")
    private String university;
    @NotNull(message = "interestArr is empty")
    private List<String> interestArr;
}
