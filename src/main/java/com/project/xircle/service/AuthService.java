package com.project.xircle.service;

import com.project.xircle.common.exception.BaseException;
import com.project.xircle.common.response.BaseResponse;
import com.project.xircle.common.response.BaseResponseStatus;
import com.project.xircle.common.util.EntityDtoUtil;
import com.project.xircle.dto.auth.CheckEmailRequestDto;
import com.project.xircle.dto.auth.CheckNameRequestDto;
import com.project.xircle.dto.auth.CreateUserRequestDto;
import com.project.xircle.model.User;
import com.project.xircle.repository.AuthRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

import static com.project.xircle.common.response.BaseResponseStatus.*;


@Service
@AllArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final ReactiveRedisOperations<String, String> reactiveRedisOperations;


    public Mono<BaseResponse> checkEmail(CheckEmailRequestDto emailRequestDto) {
        return  authRepository.findByEmail(emailRequestDto.getEmail())
                .flatMap(user ->Mono.just(new BaseResponse(ALREADY_EXIST_EMAIL)))
                .switchIfEmpty(reactiveRedisOperations.keys(emailRequestDto.getEmail())
                        .next()
                        .filter(v -> v.equals(emailRequestDto.getCode()))
                        .flatMap(v->Mono.just(new BaseResponse(SUCCESS)))
                        .switchIfEmpty(Mono.just(new BaseResponse(NOT_EQUAL_CODE))));


    }

    public Mono<BaseResponse> checkName(CheckNameRequestDto nameRequestDto) {
        return authRepository.findByDisplayName(nameRequestDto.getDisplayName())
                .flatMap(user -> Mono.just(new BaseResponse(ALREADY_EXIST_NAME)))
                .switchIfEmpty(Mono.just(new BaseResponse(SUCCESS)));
    }


    public Mono<BaseResponse> createUser(CreateUserRequestDto userRequestDto,Mono<FilePart> profileImg) {
        Path basePath= Paths.get("./src/main/resources/static/");
        User user= EntityDtoUtil.toEntity(userRequestDto);

        return profileImg
                .doOnNext(file-> user.setProfileImgSrc(file.filename()))
                .flatMap(file->file.transferTo(basePath.resolve(file.filename())))
                .then(authRepository.save(user)
                        .then(Mono.just(new BaseResponse(SUCCESS))));
    }
}
