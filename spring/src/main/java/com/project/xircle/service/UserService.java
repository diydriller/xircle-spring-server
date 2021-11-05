package com.project.xircle.service;

import com.project.xircle.dto.user.*;
import com.project.xircle.error.BaseException;
import com.project.xircle.model.Interest;
import com.project.xircle.model.User;
import com.project.xircle.repository.InterestRepository;
import com.project.xircle.repository.UserRepository;
import com.project.xircle.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.xircle.response.BaseResponseStatus.*;


@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${file.upload.path}")
    private String fileUploadPath;
    @Value("${file.down.path}")
    private String fileDownPath;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final InterestRepository interestRepository;

    public User checkToken(String token) {
        Long id=AuthUtil.getId(token);
        return userRepository.findById(id)
                .orElseThrow(()->{throw new BaseException(NOT_EXIST_USER);});
    }

    public void checkEmail(String email) {
        userRepository.findByEmail(email)
                .ifPresent(e->{throw new BaseException(ALREADY_EXIST_EMAIL);});
    }

    public void checkName(String name) {
        userRepository.findByDisplayName(name)
                .ifPresent((n)->{throw new BaseException(ALREADY_EXIST_NAME);});
    }

    public void join(JoinRequestDto requestDto) throws Exception {
        String imageUploadUrl=imageUpload(requestDto.getProfileImg());
        String hashedPassword=passwordEncoder.encode(requestDto.getPassword());

        List<Interest> interestList=requestDto.getInterestArr()
                .stream()
                .map(i->Interest.createInterest(i))
                .collect(Collectors.toList());

        User user=User.createUser(requestDto.getAge(),requestDto.getAdjective(), requestDto.getEmail(),
                requestDto.getGender(), requestDto.getIntroduction(),requestDto.getJob(),
                requestDto.getDisplayName(), requestDto.getAddress(),requestDto.getUniversity(),
                requestDto.isPublic(), requestDto.isGraduate(), requestDto.getPhoneNumber(),
                requestDto.getWorkPlace(), requestDto.getResume(), requestDto.isLocationPublic(),
                requestDto.getLongitude(),requestDto.getLatitude(),interestList
        );

        user.setProfileImgSrc(imageUploadUrl);
        user.setPassword(hashedPassword);
        userRepository.save(user);

    }


    public LoginResponseDto login(LoginRequestDto requestDto) {
        User user= userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(()->{throw new BaseException(NOT_EXIST_EMAIL);});

        if(passwordEncoder.matches(user.getPassword(), requestDto.getPassword())){
            throw new BaseException(NOT_EQUAL_PASSWORD);
        }

        return new LoginResponseDto(AuthUtil.getToken(user),user.getId());
    }

    public GetProfileResponseDto getProfile(Long id,Long userId){
        PageRequest pageRequest = PageRequest.of(0,1);
        User user=(userId==0
                ?userRepository.findRandomUserById(id,pageRequest).get(0)
                :userRepository.findUserById(userId,pageRequest).get(0));

        GetProfileResponseDto responseDto=new GetProfileResponseDto();
        BeanUtils.copyProperties(user,responseDto);
        responseDto.setInterestArr(interestRepository.findSimilarInterest(user));
        return responseDto;
    }


    public List<GetUserResponseDto> getUsers(GetUserCondition condition) {
        return userRepository.findUserByCondition(condition);
    }


    // 이미지 업로드 함수
    String imageUpload(MultipartFile imageFile) throws BaseException, IOException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String current_date = simpleDateFormat.format(new Date());

        String basePath = new File("").getAbsolutePath()+fileUploadPath;


        String[] imageFileFlags = imageFile.getOriginalFilename().split("\\.");
        String imageExt=imageFileFlags[imageFileFlags.length-1];

        String imagePath = basePath + "user" + current_date + "." + imageExt;
        String imageDownUrl = fileDownPath + "user" + current_date + "." + imageExt;

        File dest = new File(imagePath);
        imageFile.transferTo(dest);

        return imageDownUrl;
    }


}
