package com.project.xircle.service;

import com.project.xircle.common.response.BaseResponse;
import com.project.xircle.common.util.AuthUtil;
import com.project.xircle.common.util.EntityDtoUtil;
import com.project.xircle.dto.auth.CheckEmailRequestDto;
import com.project.xircle.dto.auth.CheckNameRequestDto;
import com.project.xircle.dto.auth.CreateUserRequestDto;
import com.project.xircle.dto.auth.LoginRequestDto;
import com.project.xircle.model.User;
import com.project.xircle.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.project.xircle.common.response.BaseResponseStatus.*;


@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${file.upload.path}")
    private String fileUploadPath;
    @Value("${file.down.path}")
    private String fileDownPath;



    private final UserRepository userRepository;
    private final ReactiveRedisOperations<String, String> reactiveRedisOperations;
    private final PasswordEncoder passwordEncoder;

    public Mono<BaseResponse> checkEmail(CheckEmailRequestDto emailRequestDto) {
        return  userRepository.findByEmail(emailRequestDto.getEmail())
                .flatMap(user ->Mono.just(new BaseResponse(ALREADY_EXIST_EMAIL)))
                .switchIfEmpty(reactiveRedisOperations.opsForValue().get(emailRequestDto.getEmail())
                        .filter(v ->v.equals(emailRequestDto.getCode()))
                        .flatMap(v->Mono.just(new BaseResponse(SUCCESS)))
                        .switchIfEmpty(Mono.just(new BaseResponse(NOT_EQUAL_CODE))));
    }


    public Mono<BaseResponse> checkName(CheckNameRequestDto nameRequestDto) {
        return userRepository.findByDisplayName(nameRequestDto.getDisplayName())
                .flatMap(u -> Mono.just(new BaseResponse(ALREADY_EXIST_NAME)))
                .switchIfEmpty(Mono.just(new BaseResponse(SUCCESS)));
    }


    public Mono<BaseResponse> createUser(CreateUserRequestDto userRequestDto,Mono<FilePart> profileImg) {
        Path basePath= Paths.get(fileUploadPath);
        User user= EntityDtoUtil.toEntity(userRequestDto);

        return profileImg
                .flatMap(f->{
                    String currentDate = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                    String imageExt = f.filename().split("\\.")[f.filename().split("\\.").length-1];
                    String filename=currentDate+"."+imageExt;
                    user.setProfileImgSrc(fileDownPath+filename);
                    user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
                    return f.transferTo(basePath.resolve(filename));
                })
                .then(userRepository.save(user)
                        .map(u-> AuthUtil.getToken(u))
                        .flatMap(s->Mono.just(new BaseResponse(s))));
    }


    public Mono<BaseResponse> login(LoginRequestDto requestDto) {
        return userRepository.findByDisplayName(requestDto.getDisplayName())
                .flatMap(u->{
                    if(passwordEncoder.matches(requestDto.getPassword(),u.getPassword())){
                        return Mono.just(new BaseResponse(AuthUtil.getToken(u)));
                    }
                    return Mono.just(new BaseResponse(NOT_EQUAL_PASSWORD));
                })
                .switchIfEmpty(Mono.just(new BaseResponse(NOT_EXIST_USER)));
    }

}

