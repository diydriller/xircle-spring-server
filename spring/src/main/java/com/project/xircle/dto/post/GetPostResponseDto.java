package com.project.xircle.dto.post;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GetPostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String postImgSrc;
    private String createdAt;
    private List<String> hashtag=new ArrayList<>();
}
