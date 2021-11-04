package com.project.xircle.dto.user;

import com.project.xircle.dto.interest.GetProfileDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GetProfileResponseDto {
    private String profileImgSrc;
    private String adjective;
    private String job;
    private String displayName;
    private String gender;
    private String university;
    private boolean isGraduate;
    private boolean isPublic;
    private String address;
    private int age;
    private String resume;
    private String workPlace;
    private String introduction;
    private boolean isLocationPublic;
    private double latitude;
    private double longitude;
    private List<GetProfileDto> interestArr=new ArrayList<>();
}
