package com.project.xircle.controller;

import com.project.xircle.common.response.BaseResponse;
import com.project.xircle.dto.auth.CheckEmailRequestDto;
import com.project.xircle.dto.auth.CheckNameRequestDto;
import com.project.xircle.dto.auth.CreateUserRequestDto;
import com.project.xircle.dto.auth.LoginRequestDto;
import com.project.xircle.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;



@RestController
@AllArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/check/email")
    Mono<BaseResponse> checkEmail(@RequestBody @Valid CheckEmailRequestDto emailRequestDto){
        return userService.checkEmail(emailRequestDto);
    }

    @PostMapping("/check/name")
    Mono<BaseResponse> checkName(@RequestBody @Valid CheckNameRequestDto nameRequestDto){
        return userService.checkName(nameRequestDto);
    }

    @PostMapping("/user")
    Mono<BaseResponse> createUser(CreateUserRequestDto userRequestDto, @RequestPart("profileImg") Mono<FilePart> file){
        return userService.createUser(userRequestDto,file);
    }

    @PostMapping("/login")
    Mono<BaseResponse> login(@RequestBody @Valid LoginRequestDto requestDto){
        return userService.login(requestDto);

    }

}
