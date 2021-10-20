package com.project.xircle.controller;

import com.project.xircle.common.response.BaseResponse;
import com.project.xircle.dto.post.CreatePostRequestDto;
import com.project.xircle.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    Mono<BaseResponse> createPost(@Valid CreatePostRequestDto requestDto,
                                  @RequestPart Mono<FilePart> postImgSrc,
                                  @RequestHeader("access_token") String token){
        return postService.createPost(requestDto,postImgSrc,token);
    }
}
