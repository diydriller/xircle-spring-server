package com.project.xircle.controller;

import com.project.xircle.dto.post.CreatePostRequestDto;
import com.project.xircle.error.BaseException;
import com.project.xircle.model.User;
import com.project.xircle.response.BaseResponse;
import com.project.xircle.service.PostService;
import com.project.xircle.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.project.xircle.response.BaseResponseStatus.SERVER_ERROR;
import static com.project.xircle.response.BaseResponseStatus.SUCCESS;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping("/post")
    BaseResponse<?> createPost(
            @Valid CreatePostRequestDto requestDto,
            @RequestHeader(value = "access_token") String token
    ){
        try{
            User user = userService.checkToken(token);
            postService.createPost(user,requestDto);
            return new BaseResponse(SUCCESS);
        }
        catch (BaseException e){
            return new BaseResponse(e.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(SERVER_ERROR);
        }
    }

}
