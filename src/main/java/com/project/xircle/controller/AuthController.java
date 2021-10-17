package com.project.xircle.controller;

import com.project.xircle.common.exception.BaseException;
import com.project.xircle.common.response.BaseResponse;
import com.project.xircle.dto.auth.CheckEmailRequestDto;
import com.project.xircle.dto.auth.CheckNameRequestDto;
import com.project.xircle.dto.auth.CreateUserRequestDto;
import com.project.xircle.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;



@RestController
@AllArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping("/check/email")
    Mono<BaseResponse> checkEmail(@RequestBody @Valid CheckEmailRequestDto emailRequestDto){
        return authService.checkEmail(emailRequestDto);
    }

    @PostMapping("/check/name")
    Mono<BaseResponse> checkName(@RequestBody @Valid CheckNameRequestDto nameRequestDto){
        return authService.checkName(nameRequestDto);
    }

    @PostMapping("/user")
    Mono<BaseResponse> createUser(CreateUserRequestDto userRequestDto, @RequestPart("profileImg") Mono<FilePart> file){
        return authService.createUser(userRequestDto,file);
    }

}
