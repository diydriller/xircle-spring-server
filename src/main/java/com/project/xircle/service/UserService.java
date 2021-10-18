package com.project.xircle.service;

import com.project.xircle.common.response.BaseResponse;
import com.project.xircle.common.util.AuthUtil;
import com.project.xircle.common.util.EntityDtoUtil;
import com.project.xircle.dto.auth.CheckEmailRequestDto;
import com.project.xircle.dto.auth.CheckNameRequestDto;
import com.project.xircle.dto.auth.CreateUserRequestDto;
import com.project.xircle.model.User;
import com.project.xircle.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.project.xircle.common.response.BaseResponseStatus.*;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ReactiveRedisOperations<String, String> reactiveRedisOperations;


    public Mono<BaseResponse> checkEmail(CheckEmailRequestDto emailRequestDto) {
        return  userRepository.findByEmail(emailRequestDto.getEmail())
                .flatMap(user ->Mono.just(new BaseResponse(ALREADY_EXIST_EMAIL)))
                .switchIfEmpty(reactiveRedisOperations.keys(emailRequestDto.getEmail())
                        .next()
                        .filter(v -> v.equals(emailRequestDto.getCode()))
                        .flatMap(v->Mono.just(new BaseResponse(SUCCESS)))
                        .switchIfEmpty(Mono.just(new BaseResponse(NOT_EQUAL_CODE))));


    }

    public Mono<BaseResponse> checkName(CheckNameRequestDto nameRequestDto) {
        return userRepository.findByDisplayName(nameRequestDto.getDisplayName())
                .flatMap(user -> Mono.just(new BaseResponse(ALREADY_EXIST_NAME)))
                .switchIfEmpty(Mono.just(new BaseResponse(SUCCESS)));
    }


    public Mono<BaseResponse> createUser(CreateUserRequestDto userRequestDto,Mono<FilePart> profileImg) {
        Path basePath= Paths.get("./src/main/resources/static/");
        User user= EntityDtoUtil.toEntity(userRequestDto);

        return profileImg
                .doOnNext(f-> user.setProfileImgSrc(f.filename()))
                .flatMap(f->f.transferTo(basePath.resolve(f.filename())))
                .then(userRepository.save(user)
                        .map(u-> AuthUtil.getToken(u))
                        .flatMap(s->Mono.just(new BaseResponse(s))));
    }
}
