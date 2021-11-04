package com.project.xircle.controller;

import com.project.xircle.dto.user.CheckEmailRequestDto;
import com.project.xircle.dto.user.CheckNameRequestDto;
import com.project.xircle.dto.user.JoinRequestDto;
import com.project.xircle.dto.user.LoginRequestDto;
import com.project.xircle.error.BaseException;
import com.project.xircle.response.BaseResponse;
import com.project.xircle.service.UserService;
import com.project.xircle.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.project.xircle.response.BaseResponseStatus.SERVER_ERROR;
import static com.project.xircle.response.BaseResponseStatus.SUCCESS;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/check/email")
    BaseResponse<?> checkEmail(@RequestBody @Valid CheckEmailRequestDto requestDto){
        try{
            userService.checkEmail(requestDto.getEmail());
            return new BaseResponse(SUCCESS);
        }
        catch(BaseException e){
            return new BaseResponse(e.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(SERVER_ERROR);
        }
    }

    @PostMapping("/check/name")
    BaseResponse<?> checkName(@RequestBody @Valid CheckNameRequestDto requestDto){
        try{
            userService.checkName(requestDto.getName());
            return new BaseResponse(SUCCESS);
        }
        catch(BaseException e){
            return new BaseResponse(e.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    BaseResponse<?> join(@Valid JoinRequestDto requestDto){
        try{
            userService.join(requestDto);
            return new BaseResponse(SUCCESS);
        }
        catch (BaseException e){
            return new BaseResponse(e.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    BaseResponse<?> login(@RequestBody @Valid LoginRequestDto requestDto){
        try{
            return new BaseResponse(userService.login(requestDto));
        }
        catch (BaseException e){
            return new BaseResponse(e.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}/profile")
    BaseResponse<?> getProfile(
            @PathVariable("userId") Long userId,
            @RequestHeader(value = "access_token") String token
    ){
        try{
            Long id = userService.checkToken(token).getId();
            return new BaseResponse(userService.getProfile(id,userId));
        }
        catch (BaseException e){
            return new BaseResponse(e.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(SERVER_ERROR);
        }
    }








}
